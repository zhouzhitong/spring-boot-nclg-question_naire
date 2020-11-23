package com.nclg.controller;

import com.nclg.entity.ExamAnswer;
import com.nclg.entity.ExamInfo;
import com.nclg.mapper.ExamAnswerMapper;
import com.nclg.mapper.ExamInfoMapper;
import com.nclg.query.ExamInfoVoQuery;
import com.nclg.service.ExamInfoService;
import com.nclg.util.ExamAnswerUtils;
import com.nclg.vo.ExamInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 描述：<br>表现层: 问卷中的 问题管理
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 14:50
 **/
@Controller
@RequestMapping(value = "/admin/examInfo")
public class ExamInfoController {

    @Resource
    private ExamInfoVoQuery examInfoVoQuery;

    @GetMapping("/info/{id}")
    public String getOneExamInfo(@PathVariable("id") Long id, Model model) {
        ExamInfoVo examInfoVo = examInfoVoQuery.getOneById(id);
        System.out.println(examInfoVo);
        model.addAttribute("examInfoVo", examInfoVo);
        model.addAttribute("info", "true");
        return "admin/examInfo/show_add";
    }

    /**
     * 处理试题更新请求
     *
     * @param id    试题的ID
     * @param model 相当于 ServletRequest
     * @return 返回到添加页面 "admin/teacher/add"
     **/
    @GetMapping(value = "/edit/{id}")
    public String updateExamInfoVo(@PathVariable("id") Long id, Model model) {
        ExamInfoVo examInfoVo = examInfoVoQuery.getOneById(id);
        model.addAttribute("examInfoVo", examInfoVo);
        return "admin/examInfo/show_add";
    }

    @Resource
    private ExamInfoService examInfoService;

    /**
     * 编辑问题
     *
     * @param examInfo 问卷信息
     * @param request 存储 问题相关信息
     * @param model 响应消息
     * @return 重定向至列表页面
     */
    @PutMapping("/edit")
    public String updateCheckExamInfoVo(ExamInfo examInfo, HttpServletRequest request, Model model) {
        List<ExamAnswer> examAnswers = ExamAnswerUtils.packageExamAnswerInfo(request);
        if (examAnswers != null) {
            try {
                int count = examInfoService.updateExamInfoAndAnswer(examInfo, examAnswers);
                if (count != 1) {
                    model.addAttribute("operate_msg", "操作失败！！！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("operate_msg", "操作失败！！！");
            }
        } else {
            model.addAttribute("operate_msg", "操作失败！！！");
        }

        return "redirect:/admin/paper/paperList";
    }

}
