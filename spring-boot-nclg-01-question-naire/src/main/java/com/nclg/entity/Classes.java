package com.nclg.entity;

import lombok.Data;

/**
 * (Classes)实体类
 *
 * @author 周志通
 * @since 2020-09-20 10:11:26
 */
@Data
public class Classes {

    private Integer id;
    /**
     * 专业名称
     */
    private Integer subjectId;
    /**
     * 专业班级
     */
    private String classesName;
    /**
     * 班级备注
     */
    private String remarks;

}