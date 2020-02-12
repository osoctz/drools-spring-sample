package com.zhongcheng.drools.spring.sample.service;

import com.zhongcheng.drools.spring.sample.entity.QueryParam;

public interface RuleEngineService {
    void executeAddRule (QueryParam param) ;
    void executeRemoveRule (QueryParam param) ;
}