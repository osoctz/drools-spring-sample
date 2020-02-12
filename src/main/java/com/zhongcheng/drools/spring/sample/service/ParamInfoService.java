package com.zhongcheng.drools.spring.sample.service;

import com.zhongcheng.drools.spring.sample.entity.ParamInfo;

public interface ParamInfoService {
    ParamInfo selectById (String paramId) ;
    void insertParam (ParamInfo paramInfo) ;
}