package com.nclg.util;

import com.nclg.entity.ExamAnswer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 **/
public class ExamAnswerUtils {

    /**
     * 封装
     *
     * @param request 请求体
     * @return 封装体 {@link List <ExamAnswer>}
     */
    public static List<ExamAnswer> packageExamAnswerInfo(HttpServletRequest request) {
        List<ExamAnswer> examAnswers = null;
        try {
            examAnswers = new ArrayList<>();
            System.out.println(Arrays.asList(request.getParameterValues("id")));
            Long id = Long.valueOf(request.getParameter("id"));
            String[] examAnswerId = request.getParameterValues("examAnswer.id");
            String[] answerId = request.getParameterValues("examAnswer.answerId");
            String[] content = request.getParameterValues("examAnswer.content");
//            String[] remarks = request.getParameterValues("examAnswer.remarks");
            System.out.println(Arrays.toString(examAnswerId));
            System.out.println(Arrays.toString(answerId));
            System.out.println(Arrays.toString(content));
//            System.out.println(Arrays.toString(remarks));
            for (int i = 0; i < examAnswerId.length; i++) {
                ExamAnswer answer = new ExamAnswer();
                answer.setId(Long.valueOf(examAnswerId[i])).setExamId(id).setAnswerId(answerId[i])
                        .setContent(content[i]);
                examAnswers.add(answer);
            }
            return examAnswers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
