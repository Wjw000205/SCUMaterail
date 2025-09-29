package com.kdde.basemodule.basemodule.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hte_vivo")
public class HteVivoEntity implements Serializable {

  private String poreShape;
  private String modelPorosity;
  private String specificSurfaceArea;
  private String permeability;

//  @TableField("`BV/TV`")
  private String bvTv;

  private String effectivePorosity;


}
