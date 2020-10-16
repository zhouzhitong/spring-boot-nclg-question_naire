package com.nclg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 描述：<br>
 * </>
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 10:13
 **/
@Setter
@Getter
@ToString
public class NaireTypeVo {

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

    private String departmentName;

    private String subjectName;

    private String classesName;

    /**
     * 备注信息
     */
    private String remarks;

}
