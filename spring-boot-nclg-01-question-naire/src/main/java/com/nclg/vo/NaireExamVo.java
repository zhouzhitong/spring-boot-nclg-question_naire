package com.nclg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 10:33
 **/
@Setter
@Getter
@ToString
public class NaireExamVo {

    /**
     * 主键ID
     */
    private Long id;

    private Long examId;
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
