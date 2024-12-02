package com.zhongcheng.drools.spring.sample.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * RuleResult
 *
 * @author : 1191
 * @version 1.0
 * @date: 2020-02-12 14:03
 */
@Setter
@Getter
public class RuleResult {
    private boolean postCodeResult = false ;
    private String msg;
}
