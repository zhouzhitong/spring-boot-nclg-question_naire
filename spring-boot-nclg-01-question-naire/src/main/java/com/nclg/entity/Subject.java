package com.nclg.entity;

import lombok.Data;

/**
 * (Subject)实体类
 *
 * @author 周志通
 * @since 2020-09-20 10:11:48
 */
@Data
public class Subject {
    /**
     * 专业 自增主键ID
     */
    private Integer id;
    /**
     * 院系id
     */
    private Integer departmentId;
    /**
     * 专业名称
     */
    private String subjectName;
    /**
     * 专业的备注信息
     */
    private String remarks;

    public Subject(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Subject() {
    }
}