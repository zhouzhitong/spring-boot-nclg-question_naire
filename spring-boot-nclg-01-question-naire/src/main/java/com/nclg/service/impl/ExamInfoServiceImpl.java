package com.nclg.service.impl;

import com.nclg.entity.ExamAnswer;
import com.nclg.entity.ExamInfo;
import com.nclg.entity.Naireexam;
import com.nclg.entity.Questionnaire;
import com.nclg.mapper.ExamAnswerMapper;
import com.nclg.mapper.ExamInfoMapper;
import com.nclg.mapper.NaireexamMapper;
import com.nclg.mapper.QuestionnaireMapper;
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
 * @date 2020/9/20 15:39
 **/
@Service
public class ExamInfoServiceImpl implements ExamInfoService {

    @Resource
    private ExamAnswerMapper examAnswerMapper;
    @Resource
    private ExamInfoMapper examInfoMapper;

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
