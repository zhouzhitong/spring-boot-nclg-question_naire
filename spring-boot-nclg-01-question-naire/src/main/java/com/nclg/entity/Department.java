package com.nclg.entity;

import lombok.Data;

/**
 * (Department)实体类
 *
 * @author 周志通
 * @since 2020-09-20 10:11:43
 */
@Data
public class Department {
    /**
     * 自增主键ID
     */
    private Integer id;
    /**
     * 院系名称
     */
    private String departmentName;
    /**
     * 备注信息
     */
    private String remarks;

}