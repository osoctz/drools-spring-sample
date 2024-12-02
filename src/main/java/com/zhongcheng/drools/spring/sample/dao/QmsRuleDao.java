package com.zhongcheng.drools.spring.sample.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongcheng.drools.spring.sample.entity.QmsRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QmsRuleDao extends BaseMapper<QmsRule> {
}
