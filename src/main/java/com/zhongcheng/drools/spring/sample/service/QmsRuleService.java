package com.zhongcheng.drools.spring.sample.service;

import com.zhongcheng.drools.spring.sample.dao.QmsRuleDao;
import com.zhongcheng.drools.spring.sample.entity.QmsRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QmsRuleService {

    @Autowired
    private QmsRuleDao qmsRuleDao;

    public List<QmsRule> list() {

        return qmsRuleDao.selectList(null);
    }
}
