package com.nclg.util;

import com.nclg.constant.ExamInfoTypeConstant;
import com.nclg.entity.ExamAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/7/1 21:27
 **/
public class ExamDataTransformUtils {

    public static List<ExamAnswer> dataTransform(Long id , List<String> lists, String type){
        List<ExamAnswer> examAnswers = new ArrayList<>() ;
        switch (type.trim()){
            // 单选题
            case ExamInfoTypeConstant.SINGLE_CHOICE : {
                def(id, lists, examAnswers);
                break;
            }
            // 多选题
            case ExamInfoTypeConstant.MULTIPLE_CHOICE: {
                def(id, lists, examAnswers);
                examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("E").setContent(lists.get(4))) ;
                examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("F").setContent(lists.get(4))) ;

                break;
            }
            // 判断题
            case ExamInfoTypeConstant.TRUE_OR_FALSE: {
                examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("A").setContent(lists.get(1))) ;
                examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("B").setContent(lists.get(2))) ;
                break;
            }
            // 简答题
            /*case ExamInfoTypeConstant.SHORTANSWERQUESTIONS: {
                examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("1").setContent(lists.get(1))) ;
                break;
            }*/
            default:{
                System.out.println("选择了错误的题型");
                throw new RuntimeException("错误的题型，不存在该题型") ;
            }

        }
        return examAnswers ;
    }



    private static void def(Long id, List<String> lists, List<ExamAnswer> examAnswers) {
        examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("A").setContent(lists.get(1))) ;
        examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("B").setContent(lists.get(2))) ;
        examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("C").setContent(lists.get(3))) ;
        examAnswers.add(new ExamAnswer().setExamId(id).setAnswerId("D").setContent(lists.get(4))) ;
    }
}
