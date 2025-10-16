package com.kdde.basemodule.basemodule.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModuleDao extends BaseMapper<ModuleEntity> {
}
