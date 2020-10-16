package com.nclg.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (ExamAnswer)实体类
 *
 * @author 周志通
 * @since 2020-09-20 12:56:29
 */
@Data
@Accessors(chain = true)
public class ExamAnswer {
    /**
     * 主键：bigint
     */
    private Long id;
    /**
     * 外键[exam_info.exam_id]:long
     */
    private Long examId;
    /**
     * 选项序号：varchar
     */
    private String answerId;
    /**
     * 选项信息：varchar[50]
     */
    private String content;
    /**
     * 备注信息：varchar[255]
     */
    private String remarks;

}