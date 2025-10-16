package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("module_conflict_table")
public class ModuleLinkTableEntity {

    @TableId
    private int id;

    private int tableId;

    private String objectTableName;

    private String operationTableName;

    private String resultTableName;
}
