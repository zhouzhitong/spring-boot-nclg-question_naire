package com.nclg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * (Questionnaire)实体类
 *
 * @author 周志通
 * @since 2020-09-20 21:06:49
 */
@Data
@Accessors(chain = true)
public class Questionnaire {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 问卷名称
     */
    private String naireName;

    private String type;
    /**
     * 问卷生成日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date naireDate;
    /**
     * 备注信息
     */
    private String naireRemarks;

}