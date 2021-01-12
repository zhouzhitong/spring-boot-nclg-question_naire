package com.nclg.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (NaireType)实体类
 *
 * @author 周志通
 * @since 2020-09-20 09:49:51
 */
@Data
@Accessors(chain = true)
public class NaireType {
    /**
     * 自增主键ID
     */
    private Integer id;
    /**
     * 问卷 id
     */
    private Long naireId;
    /**
     * 问卷类型[学生/老师]
     */
    private String type;
    /**
     * 院系 id
     */
    private Integer departmentId;
    /**
     * 专业 id
     */
    private Integer subjectId;
    /**
     * 班级 id
     */
    private Integer classesId;
    /**
     * 备注信息
     */
    private String remarks;

}