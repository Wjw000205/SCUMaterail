package com.kdde.basemodule.basemodule.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hte_vitro")
public class HteVitroEntity implements Serializable {

  private String poreShape;
  private Double modelPorosity;
  private Double specificSurfaceArea;
  private Double permeability;
  private Long day;
  private Double alp;

  @TableField("`COL-1`")
  private Double col_1;

  private Double ocn;
  private Double opn;

  @TableField("`RUNX-2`")
  private Double runx_2;


}
