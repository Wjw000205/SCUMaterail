package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("module_structure")
public class ModuleStructureEntity {
    int id;

    int moduleId;

    String columnName;

    String columnContribution;

    LocalDate createTime;

    String belong; //属于Object、Operation、Result中的哪一类
}
