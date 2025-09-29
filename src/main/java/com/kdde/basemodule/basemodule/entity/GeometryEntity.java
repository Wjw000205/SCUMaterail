package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("geometry")
public class GeometryEntity implements Serializable {

  private Long numberExperiment;
  private Long curveId;
  private Long graphId;
  private Double Q;
  private Double diameterShrinkageRate;
  private Double highShrinkageRate;
  private Long porosity;
  private Long id;
  private Long source;
  private Long target;
  private Long weight;
  private Long edgeId;
  private Long idx;
  
//  @TableField("`id_0`")
  private Long id_0;
  
  private Double X;
  private Double Y;
  private Long Z;
  private Long feature;
  private Long active;

}
