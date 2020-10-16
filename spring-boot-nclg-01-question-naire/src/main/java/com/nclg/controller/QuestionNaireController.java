package com.nclg.controller;

import com.nclg.entity.Questionnaire;
import com.nclg.mapper.QuestionnaireMapper;
import com.nclg.query.NaireTypeVoQuery;
import com.nclg.query.QuestionNaireVoQuery;
import com.nclg.service.ExamInfoService;
import com.nclg.vo.NaireExamVo;
import com.nclg.vo.NaireTypeVo;
import com.nclg.vo.QuestionNaireVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 8:37
 **/
@Controller
@RequestMapping(value = "/admin/paper")
public class QuestionNaireController {

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @GetMapping(value = "paperList")
    public String jumpPaperList(Model model) {
        List<Questionnaire> questionnaires = questionnaireMapper.listByEntity(null);
        model.addAttribute("questionnaires", questionnaires);
        return "admin/paper/paperList";
    }

    @PostMapping(value = "/edit")
    public String paperInsert(Questionnaire questionnaire) {
        questionnaire.setNaireDate(new Date());
        int insert = questionnaireMapper.insert(questionnaire);
        System.out.println(insert);
        return "redirect:/admin/paper/paperList";
    }

    @Resource
    private QuestionNaireVoQuery questionNaireVoQuery;

    @Resource
    private NaireTypeVoQuery naireTypeVoQuery;

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
        NaireTypeVo naireTypeVo = naireTypeVoQuery.findOneByNaireId(id);
        Questionnaire questionnaire = questionnaireMapper.getById(id);
        System.out.println(questionnaire);
        model.addAttribute("naireExamVos", naireExamVos);
        model.addAttribute("questionnaire", questionnaire);
        return "admin/paper/paperExamInfoList";
    }


// Excel文件导入请求

    @Resource
    private ExamInfoService examInfoService;

    @GetMapping(value = "excel_import/{id}")
    public String importExcel(@PathVariable("id") Long id, Model model) {
        model.addAttribute("paperId", id);
        return "admin/paper/excelImport";
    }

    @PostMapping(value = "excel_import")
    public String importExcel(@RequestParam("type") String type, Long paperId
            , @RequestParam("excel_file") MultipartFile excelFile) throws IOException {
        examInfoService.excelImportService(type, paperId, excelFile.getInputStream(), excelFile.getOriginalFilename());
        return "redirect:/admin/paper/info/" + paperId;
    }


}
