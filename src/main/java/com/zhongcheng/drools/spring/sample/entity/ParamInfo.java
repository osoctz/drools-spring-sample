package com.zhongcheng.drools.spring.sample.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * ParamInfo
 *
 * @author : 1191
 * @version 1.0
 * @date: 2020-02-12 14:02
 */
@Setter
@Getter
public class ParamInfo {

    private String id ;
    private String paramSign ;
    private Date createTime ;
    private Date updateTime ;
}
