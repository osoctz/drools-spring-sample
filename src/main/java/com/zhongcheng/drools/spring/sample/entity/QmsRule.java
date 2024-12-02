package com.zhongcheng.drools.spring.sample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
 
/**
 * @author zhangkx1.ziroom.com
 * @create 2022/3/4 11:21
 */
@Data
@TableName(value = "qms_rule")
public class QmsRule implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
 
    /**
     * 规则标识
     */
    @TableField(value = "rule_code")
    private String ruleCode;
 
    /**
     * 规则数据
     */
    @TableField(value = "rule_condition")
    private String ruleCondition;
 
    private static final long serialVersionUID = 1L;
}
