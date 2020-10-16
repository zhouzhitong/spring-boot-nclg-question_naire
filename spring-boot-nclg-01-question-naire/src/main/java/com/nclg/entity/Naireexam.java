package com.nclg.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (Naireexam)实体类
 *
 * @author 周志通
 * @since 2020-09-20 10:08:01
 */
@Data
@Accessors(chain = true)
public class Naireexam {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 问卷编号ID
     */
    private Long naireId;
    /**
     * 试题编号ID
     */
    private Long examId;
    /**
     * 问卷备注信息
     */
    private String infoRemarks;

}