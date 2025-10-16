package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("module_parent")
public class ModuleParentEntity {
    int id;
    String name;
    Date create_time;
}
