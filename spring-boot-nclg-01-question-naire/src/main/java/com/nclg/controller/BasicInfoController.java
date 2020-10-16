package com.nclg.controller;

import com.nclg.entity.Classes;
import com.nclg.entity.Department;
import com.nclg.entity.Subject;
import com.nclg.mapper.ClassesMapper;
import com.nclg.mapper.DepartmentMapper;
import com.nclg.mapper.SubjectMapper;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/10/11 15:43
 **/
@Controller
@RequestMapping(value = "/admin/basicInfo")
public class BasicInfoController {

    @Resource
    private DepartmentMapper departmentMapper;

    @GetMapping("/depart")
    public String basicInfo(Model model) {
        List<Department> departments = departmentMapper.listByEntity(null);
        model.addAttribute("departments", departments);
        return "admin/basic_info/basicInfoList_department";
    }

    public String addDepartment(){

        return "";
    }



// -----------------------------------------------------

    @Resource
    private SubjectMapper subjectMapper;

    @GetMapping("/subject/{id}")
    public String basicInfoSubject(@PathVariable("id") Integer id, Model model) {
        Subject subject = new Subject();
        subject.setDepartmentId(id);
        List<Subject> subjects = subjectMapper.listByEntity(subject);
        model.addAttribute("subjects", subjects);
        return "admin/basic_info/basicInfoList_subject";
    }

    public String addSubject(Integer departmentId,Model model){
        model.addAttribute("departmentId",departmentId);
        return "";
    }

// -----------------------------------------------------

    @Resource
    private ClassesMapper classesMapper;

    @GetMapping("/classes/{id}")
    public String basicInfoClasses(@PathVariable Integer id, Model model) {
        Classes c = new Classes();
        c.setSubjectId(id);
        List<Classes> classes = classesMapper.listByEntity(c);
        model.addAttribute("classes", classes);
        return "admin/basic_info/basicInfoList_classes";
    }

}
