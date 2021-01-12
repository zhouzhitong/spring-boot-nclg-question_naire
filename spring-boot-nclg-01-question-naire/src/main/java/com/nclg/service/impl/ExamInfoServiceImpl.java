package com.nclg.service.impl;

import com.nclg.entity.ExamAnswer;
import com.nclg.entity.ExamInfo;
import com.nclg.entity.Naireexam;
import com.nclg.entity.Questionnaire;
import com.nclg.mapper.*;
import com.nclg.service.ExamInfoService;
import com.nclg.util.ExamDataTransformUtils;
import com.nclg.util.ImportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 **/
@Service
public class ExamInfoServiceImpl implements ExamInfoService {

    @Resource
    private ExamAnswerMapper examAnswerMapper;
    @Resource
    private ExamInfoMapper examInfoMapper;
    @Resource
    private NaireTypeMapper naireTypeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateExamInfoAndAnswer(ExamInfo examInfo, List<ExamAnswer> examAnswers) throws Exception {
        int count1 = examInfoMapper.update(examInfo);
        int count2 = examAnswerMapper.updateBatch(examAnswers);
        if (count1 > 0 && count2 > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int deleteExamInfo(Long examId) {
        ExamInfo examInfo = examInfoMapper.getById(examId);
        try {
            // 删除答案
            ExamAnswer examAnswer = new ExamAnswer();
            examAnswer.setExamId(examInfo.getId());
            examAnswerMapper.deleteByEntity(examAnswer);

            // 删除问卷题目信息
            Naireexam naireexam = new Naireexam();
            naireexam.setExamId(examId);
            naireexam = naireexamMapper.getByEntity(naireexam);
            naireexamMapper.deleteByEntity(naireexam);

            // 删除问卷信息
            Long naireId = naireexam.getNaireId();
            Questionnaire questionnaire = questionnaireMapper.getById(naireId);
            String naireRemarks = questionnaire.getNaireRemarks();
            int i = Integer.parseInt(naireRemarks) - 1;
            questionnaire.setNaireRemarks(i + "");
            questionnaireMapper.update(questionnaire);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 删除问题信息
        return examInfoMapper.deleteById(examId);
    }

    @Override
    public int deleteQuestionNaire(Long id) {
        // 删除问卷题目信息
        Naireexam naireId = new Naireexam();
        naireId.setNaireId(id);
        List<Naireexam> naireexams = naireexamMapper.listByEntity(naireId);
        for (Naireexam naireexam : naireexams) {
            deleteExamInfo(naireexam.getExamId());
        }
        naireTypeMapper.deleteByNaireId(id);
        return questionnaireMapper.deleteById(id);
    }

    @Resource
    private QuestionnaireMapper questionnaireMapper;
    @Resource
    private NaireexamMapper naireexamMapper;

    /**
     * 试题文件导入
     *
     * @param type        导入的类型
     * @param paperId     问卷ID
     * @param inputStream 文件的输入流
     * @param fileName    文件名称
     */
    @Override
    public void excelImportService(String type, Long paperId, InputStream inputStream, String fileName) {
        try {
            List<List<String>> listLists = new ImportExcelUtils().getBankListByExcel(inputStream, fileName);
            for (List<String> lists : listLists) {
                ExamInfo examInfo = new ExamInfo();
                examInfo.setContent(lists.get(0))
                        .setType(type.trim());
                examInfoMapper.insert(examInfo);
                List<ExamAnswer> examAnswers = ExamDataTransformUtils.dataTransform(examInfo.getId(), lists, type);
                for (ExamAnswer answer : examAnswers) {
                    examAnswerMapper.insert(answer);
                }
                Naireexam naireexam = new Naireexam();
                naireexam.setNaireId(paperId)
                        .setExamId(examInfo.getId());
                naireexamMapper.insert(naireexam);
            }
            Questionnaire questionnaire = questionnaireMapper.getById(paperId);
            questionnaireMapper.update(questionnaire
                    .setNaireRemarks(Integer.toString(
                            Integer.parseInt(questionnaire.getNaireRemarks()) + listLists.size())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}