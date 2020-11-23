package com.nclg.service;

import com.nclg.entity.ExamAnswer;
import com.nclg.entity.ExamInfo;

import java.io.InputStream;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 15:38
 **/
public interface ExamInfoService {

    /**
     * 更新 试题的 全部信息
     *
     * @param examInfo    试题的主体信息
     * @param examAnswers 试题的选项信息
     * @return 成功返回 1
     * @throws Exception 操作事务异常
     */
    int updateExamInfoAndAnswer(ExamInfo examInfo, List<ExamAnswer> examAnswers) throws Exception;


    int deleteExamInfo(Long examId);

    /**
     * 试题文件导入
     *
     * @param type        导入的类型
     * @param paperId     问卷ID
     * @param inputStream 文件的输入流
     * @param fileName    文件名称
     */
    void excelImportService(String type, Long paperId, InputStream inputStream, String fileName);

}
