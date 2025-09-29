package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("mechanicalproperty")
public class MechanicalPropertyEntity implements Serializable {

  private Long Id;
  private Long numberExperiment;

  private Long curveId;
  private Long graphId;
  private Double Q;
  private Double diameterShrinkageRate;
  private Double highShrinkageRate;
  private Long porosity;
  

}
