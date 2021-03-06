package com.nclg.controller;

import com.nclg.entity.NaireType;
import com.nclg.entity.Questionnaire;
import com.nclg.mapper.NaireTypeMapper;
import com.nclg.mapper.QuestionnaireMapper;
import com.nclg.query.NaireTypeVoQuery;
import com.nclg.query.QuestionNaireVoQuery;
import com.nclg.service.ExamInfoService;
import com.nclg.vo.NaireExamVo;
import com.nclg.vo.NaireTypeVo;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 描述：<br> 操作 问卷信息...
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 **/
@Controller
@RequestMapping(value = "/admin/paper")
@Logger
public class QuestionNaireController {

    @Resource
    private QuestionnaireMapper questionnaireMapper;
    @Resource
    private ExamInfoService examInfoService;
    @Resource
    private QuestionNaireVoQuery questionNaireVoQuery;
//    @Resource
//    private NaireTypeVoQuery naireTypeVoQuery;

    /**
     * 问卷的详细信息
     *
     * @param id    问卷的 Id
     * @param model 相当于 Servlet 原生API
     * @return admin/paper/paperExamInfoList
     */
    @GetMapping("/info/{id}")
    public String paperOneInfo(@PathVariable(value = "id") Long id, Model model) {
        List<NaireExamVo> naireExamVos = questionNaireVoQuery.findOneByIdToGetAllInfo(id);
//        NaireTypeVo naireTypeVo = naireTypeVoQuery.findOneByNaireId(id);
        Questionnaire questionnaire = questionnaireMapper.getById(id);
        System.out.println(questionnaire);
        model.addAttribute("naireExamVos", naireExamVos);
        model.addAttribute("questionnaire", questionnaire);
        return "admin/paper/paperExamInfoList";
    }

    /**
     * 列出所有问卷信息
     *
     * @param model 所有问卷信息
     * @return admin/paper/paperList.html
     */
    @GetMapping(value = {"/paperList"})
    public String jumpPaperList(Model model) {
        List<Questionnaire> questionnaires = questionnaireMapper.listByEntity(null);
        model.addAttribute("questionnaires", questionnaires);
        return "admin/paper/paperList";
    }

    @Resource
    private NaireTypeMapper naireTypeMapper;

    /**
     * 添加问卷信息
     *
     * @param questionnaire
     * @return
     */
    @PostMapping(value = {"/edit"})
    public String doPaperInsert(Questionnaire questionnaire) {
        questionnaire.setNaireDate(new Date());
        questionnaireMapper.insert(questionnaire);
        NaireType naireType = new NaireType();
        naireType.setNaireId(questionnaire.getId())
                .setType(questionnaire.getType())
                .setDepartmentId(0)
                .setSubjectId(0)
                .setClassesId(0);
        naireTypeMapper.insert(naireType);
        return "redirect:/admin/paper/paperList";
    }

    /**
     * 删除问卷
     *
     * @param qnId 问卷 ID
     * @return
     */
    @DeleteMapping(value = {"/edit/{id}"})
    public String deletePaperInfo(@PathVariable("id") Long qnId) {
        examInfoService.deleteQuestionNaire(qnId);
        return "redirect:/admin/paper/paperList";
    }

    @PostMapping(value = "/edit/{id}")
    public String doPaperDelete(@PathVariable("id") Long paperId) {
        examInfoService.deleteQuestionNaire(paperId);
        return "redirect:/admin/paper/paperList";
    }

// Excel文件导入请求

    @GetMapping(value = "excel_import/{id}")
    public String importExcel(@PathVariable("id") Long id, Model model) {
        model.addAttribute("paperId", id);
        return "admin/paper/excelImport";
    }

    @PostMapping(value = "excel_import")
    public String importExcel(@RequestParam("type") String type, Long paperId
            , @RequestParam("excel_file") MultipartFile excelFile) throws IOException {
        examInfoService.excelImportService(type, paperId
                , excelFile.getInputStream(), excelFile.getOriginalFilename());
        return "redirect:/admin/paper/info/" + paperId;
    }


}
