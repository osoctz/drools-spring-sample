package com.zhongcheng.drools.spring.sample.entity;

import lombok.Data;
 
/**
 * @author zhangkx1.ziroom.com
 * @create 2022/3/3 18:40
 */
@Data
public class RoleDict {
 
    private Integer scheduling_cycle;
    private Integer report_term_of_validity;
    private Integer early_warning_period;
    private String code;
}
