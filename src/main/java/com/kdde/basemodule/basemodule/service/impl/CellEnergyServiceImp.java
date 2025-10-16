package com.kdde.basemodule.basemodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.dao.CellEnergyDao;
import com.kdde.basemodule.basemodule.entity.CellEnergyEntity;
import com.kdde.basemodule.basemodule.service.CellEnergyService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import static com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline;
import static com.baomidou.mybatisplus.core.toolkit.StringUtils.underlineToCamel;
import static com.kdde.basemodule.basemodule.common.utils.CamelToUnderline.camelToUnder;

@Service
public class CellEnergyServiceImp extends ServiceImpl<CellEnergyDao, CellEnergyEntity> implements CellEnergyService {

    private static final List<String> DB_COLUMNS = Arrays.asList(
            "sample_serial", "test_method", "cell_type", "cell_species",
            "cell_source", "passage_number", "cell_culture_method",
            "evaluation_type", "plate_type", "seeding_density",
            "culture_medium", "culture_medium_supplier", "cell_culture_atmosphere",
            "serum_supplier", "serum", "osteogenic_induction_medium",
            "pen_strep", "serum_concentration", "cell_culture_temperature",
            "cell_culture_ph", "test_sample_shape", "sample_morpholog",
            "sample_size_length", "sample_size_width", "sample_size_height",
            "sample_size_diameter", "sample_size_thickness", "sample_size_unit",
            "extraction_medium", "extraction_condition", "sterilization_method",
            "positive_control", "negative_control", "parallel_sample_number",
            "test_sample_sterility", "agar_diffusion_test",
            "filter_diaphragm_diffusion_test", "cell_culture_time",
            "cell_culture_time_unit", "viability_qualitative_description",
            "viability_picture"
    );

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CellEnergyEntity> page = this.page(
                new Query<CellEnergyEntity>().getPage(params),
                new QueryWrapper<CellEnergyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CellEnergyEntity queryBySerial(String sampleSerial) {
        return this.getOne(new QueryWrapper<CellEnergyEntity>().eq("sample_serial", sampleSerial));
    }

    @Override
    public int saveCellEnergy(CellEnergyEntity cellEnergyEntity) {
        String sampleSerial = cellEnergyEntity.getSampleSerial();
        int count = this.count(
                new QueryWrapper<CellEnergyEntity>().eq("sample_serial", sampleSerial)
        );
        if(count > 0){
            return -1;  //代表表中已经有该序列号
        }
        else {
            save(cellEnergyEntity);
            return 1;
        }
    }

    @Override
    public boolean removeCellEnergy(List<String> sampleSerials) {
        return this.remove(
                new QueryWrapper<CellEnergyEntity>().in("sample_serial", sampleSerials)
        );
    }

    @Override
    public boolean updateCellEnergy(String sampleSerial,Map<String,Object> body) {
        UpdateWrapper<CellEnergyEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sample_serial", sampleSerial);
        body.forEach((k, v) -> updateWrapper.set(camelToUnder(k), v));
        return this.update(null,updateWrapper);
    }

    @Override
    public String importFromExcel(MultipartFile file) throws Exception {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);

        // 1. 获取 Excel 表头（转下划线）
        List<String> excelColumns = new ArrayList<>();
        for (Cell cell : headerRow) {
            String camelName = cell.getStringCellValue().trim();
            excelColumns.add(camelToUnderline(camelName));
        }

        // 2. 校验表头
        List<String> invalid = excelColumns.stream()
                .filter(col -> !DB_COLUMNS.contains(col))
                .collect(Collectors.toList());
        if (!invalid.isEmpty()) {
            return "列名不匹配: " + String.join(", ", invalid);
        }

        // 3. 读取数据并映射到实体类
        List<CellEnergyEntity> allEntities = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            CellEnergyEntity entity = new CellEnergyEntity();
            for (int j = 0; j < excelColumns.size(); j++) {
                String colName = excelColumns.get(j);
                Cell cell = row.getCell(j);
                if (cell == null) continue;

                Object value = getCellValue(cell);
                setEntityField(entity, colName, value);
            }
            allEntities.add(entity);
        }

        // 4. 获取所有 sample_serial
        List<String> serialList = allEntities.stream()
                .map(CellEnergyEntity::getSampleSerial)
                .filter(Objects::nonNull)
                .map(String::trim)
                .collect(Collectors.toList());

        if (serialList.isEmpty()) {
            return "Excel中未找到任何样品编号（sample_serial）";
        }

        // 5. 查询数据库中已存在的 sample_serial
        List<CellEnergyEntity> existingEntities = this.list(
                new QueryWrapper<CellEnergyEntity>().in("sample_serial", serialList)
        );
        Set<String> existingSerials = existingEntities.stream()
                .map(CellEnergyEntity::getSampleSerial)
                .collect(Collectors.toSet());

        // 6. 过滤掉已存在的样品编号
        List<CellEnergyEntity> newEntities = allEntities.stream()
                .filter(e -> !existingSerials.contains(e.getSampleSerial()))
                .collect(Collectors.toList());

        // 7. 保存新数据
        if (!newEntities.isEmpty()) {
            this.saveBatch(newEntities);
        }

        // 8. 构造返回结果
        return String.format(
                "导入完成：成功插入 %d 条数据，跳过已存在样品编号 %d 条。\n已存在编号：%s",
                newEntities.size(),
                existingSerials.size(),
                existingSerials.isEmpty() ? "无" : String.join(", ", existingSerials)
        );
    }


    /** 获取 Excel 单元格值 */
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case BLANK:
                return null;
            default:
                return cell.toString();
        }
    }

    /** 反射赋值 */
    private void setEntityField(Object entity, String fieldName, Object value) {
        if (value == null) return;

        try {
            Field field = CellEnergyEntity.class.getDeclaredField(underlineToCamel(fieldName));
            field.setAccessible(true);

            Class<?> fieldType = field.getType();

            // 🚩 自动类型匹配与转换
            if (fieldType == String.class) {
                field.set(entity, String.valueOf(value));
            } else if (fieldType == int.class || fieldType == Integer.class) {
                if (value instanceof Number) {
                    field.set(entity, ((Number) value).intValue());
                } else {
                    field.set(entity, Integer.parseInt(value.toString()));
                }
            } else if (fieldType == float.class || fieldType == Float.class) {
                if (value instanceof Number) {
                    field.set(entity, ((Number) value).floatValue());
                } else {
                    field.set(entity, Float.parseFloat(value.toString()));
                }
            } else if (fieldType == double.class || fieldType == Double.class) {
                if (value instanceof Number) {
                    field.set(entity, ((Number) value).doubleValue());
                } else {
                    field.set(entity, Double.parseDouble(value.toString()));
                }
            } else {
                // 默认兜底
                field.set(entity, value);
            }
        } catch (NoSuchFieldException e) {
            // 不存在的字段直接跳过
            System.err.println("跳过无效字段：" + fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
