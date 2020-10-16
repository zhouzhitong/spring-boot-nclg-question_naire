package com.nclg.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nclg.entity.NaireType;
import com.nclg.entity.Questionnaire;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 8:40
 **/
@Setter
@Getter
@ToString
public class QuestionNaireVo extends Questionnaire implements Serializable {

    private NaireType naireType;

}
