package com.kdde.basemodule.basemodule.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelTemplateUtil {

    /**
     * 根据三个List生成Excel模板
     * 第一行为 Object / Operation / Result（自动合并单元格）
     * 第二行为对应List中的各个值
     * @return Excel文件字节数组
     */
    public static byte[] generateTemplateFromLists(List<String> objectList,
                                                   List<String> operationList,
                                                   List<String> resultList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("模板");

        // 样式
        CellStyle headerStyle = createHeaderStyle(workbook);

        // 第一行和第二行
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);

        int colIndex = 0;
        colIndex = fillBlock(sheet, row1, row2, colIndex, "Object", objectList, headerStyle);
        colIndex = fillBlock(sheet, row1, row2, colIndex, "Operation", operationList, headerStyle);
        colIndex = fillBlock(sheet, row1, row2, colIndex, "Result", resultList, headerStyle);

        // 自动调整列宽
        for (int i = 0; i < colIndex; i++) {
            sheet.autoSizeColumn(i);
        }

        // 写入字节流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();
        return bos.toByteArray();
    }

    /** 填充一个模块区块 */
    private static int fillBlock(Sheet sheet, Row row1, Row row2, int startCol,
                                 String title, List<String> list, CellStyle style) {
        if (list == null || list.isEmpty()) return startCol;
        int colCount = list.size();
        int endCol = startCol + colCount - 1;

        // 合并单元格：第一行标题跨多列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, startCol, endCol));

        // 设置模块标题
        Cell titleCell = row1.createCell(startCol);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(style);

        // 第二行：字段名
        int colIndex = startCol;
        for (String value : list) {
            Cell cell = row2.createCell(colIndex++);
            cell.setCellValue(value);
            cell.setCellStyle(style);
        }

        return colIndex;
    }

    /** 创建统一样式 */
    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
}
