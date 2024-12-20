package com.zhongcheng.drools.spring.sample.service;

import com.zhongcheng.drools.spring.sample.entity.ParamInfo;
import com.zhongcheng.drools.spring.sample.entity.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RuleEngineServiceImpl implements RuleEngineService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleEngineServiceImpl.class) ;
    @Override
    public void executeAddRule(QueryParam param) {
        LOGGER.info("参数数据:"+param.getParamId()+";"+param.getParamSign());
        ParamInfo paramInfo = new ParamInfo() ;
        paramInfo.setId(param.getParamId());
        paramInfo.setParamSign(param.getParamSign());
        paramInfo.setCreateTime(new Date());
        paramInfo.setUpdateTime(new Date());
//        ParamInfoService paramInfoService = (ParamInfoService)SpringContextUtil.getBean("paramInfoService") ;
//        paramInfoService.insertParam(paramInfo);
    }
    @Override
    public void executeRemoveRule(QueryParam param) {
        LOGGER.info("参数数据:"+param.getParamId()+";"+param.getParamSign());
//        ParamInfoService paramInfoService = (ParamInfoService)SpringContextUtil.getBean("paramInfoService") ;
//        ParamInfo paramInfo = paramInfoService.selectById(param.getParamId());
//        if (paramInfo != null){
//            paramInfoService.removeById(param.getParamId()) ;
//        }
    }
}