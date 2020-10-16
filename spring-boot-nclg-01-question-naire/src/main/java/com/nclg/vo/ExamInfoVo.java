package com.nclg.vo;

import com.nclg.entity.ExamAnswer;
import com.nclg.entity.ExamInfo;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 12:55
 **/
@Data
public class ExamInfoVo extends ExamInfo implements Serializable {

    private List<ExamAnswer> examAnswers;
}
