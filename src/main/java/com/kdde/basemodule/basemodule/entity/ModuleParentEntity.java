package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("module_parent")
public class ModuleParentEntity {

    @Schema(description = "主键，不对用户显示，查询模板")
    @TableId(type = IdType.AUTO)
    int id;

    @Schema(description = "模板大类名称")
    String name;

    @Schema(description = "创建时间")
    Date create_time;
}
