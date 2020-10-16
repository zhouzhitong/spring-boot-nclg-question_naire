package com.nclg.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (ExamInfo)实体类
 *
 * @author 周志通
 * @since 2020-09-20 10:08:45
 */
@Data
@Accessors(chain = true)
public class ExamInfo {
    /**
     * 主键：bigint
     */
    private Long id;
    /**
     * 题目内容：varchar[100]
     */
    private String content;
    /**
     * 题目类型：varchar[12]
     */
    private String type;
    /**
     * 备注信息：varchar[255]
     */
    private String remarks;

}