package com.nclg.controller;

import com.nclg.entity.Classes;
import com.nclg.entity.Department;
import com.nclg.entity.Subject;
import com.nclg.mapper.ClassesMapper;
import com.nclg.mapper.DepartmentMapper;
import com.nclg.mapper.SubjectMapper;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @GetMapping(value = {"/depart", "/depart/"})
    public String basicInfo(Model model) {
        List<Department> departments = departmentMapper.listByEntity(null);
        model.addAttribute("departments", departments);
        return "admin/basic_info/basicInfoList_department";
    }

    @GetMapping(value = {"/addDepart", "/addDepart/"})
    public String addDepartment() {
        return "admin/basic_info/department_add";
    }

    @PostMapping("/addDepart")
    public String doAddDepartment(Department department) {
        departmentMapper.insert(department);
        System.out.println(department);
        return "redirect:/admin/basicInfo/depart";
    }

    @DeleteMapping("/editDepart/{id}")
    public String doDeleteDepartment(@PathVariable("id") Integer departmentId) {
        departmentMapper.deleteById(departmentId);
        List<Subject> subjects = subjectMapper.getByDepartId(departmentId);
        if (subjects != null && subjects.size() > 0) {
//            List<Integer> subjectIds = new ArrayList<>();
            for (Subject subject : subjects) {
//                subjectIds.add(subject.getId());
                classesMapper.deleteBySubjectId(subject.getId());
            }
//            classesMapper.deleteBySubjectIds(subjectIds);
        }
        subjectMapper.deleteByDepartId(departmentId);
        return "redirect:/admin/basicInfo/depart";
    }

    // -----------------------------------------------------
    @Resource
    private SubjectMapper subjectMapper;

    @GetMapping("/subject/{id}")
    public String basicInfoSubject(@PathVariable("id") Integer id, Model model) {
        Subject subject = new Subject(id);
        List<Subject> subjects = subjectMapper.listByEntity(subject);
        model.addAttribute("subjects", subjects);
        model.addAttribute("id", id);
        return "admin/basic_info/basicInfoList_subject";
    }

    @GetMapping("/addSubject/{departmentId}")
    public String addSubject(@PathVariable("departmentId") Integer departmentId, Model model) {
        Department department = departmentMapper.getById(departmentId);
        model.addAttribute("department", department);
        return "admin/basic_info/subject_add";
    }

    @PostMapping("/addSubject/{departmentId}")
    public String doAddSubject(@PathVariable("departmentId") Integer departmentId, Subject subject) {
        subjectMapper.insert(subject);
        return "redirect:/admin/basicInfo/subject/" + departmentId;
    }

    @DeleteMapping("/editSubject/{subjectId}")
    public String doDeleteSubject(@PathVariable("subjectId") Integer subjectId, Integer departmentId) {
        subjectMapper.deleteById(subjectId);
        classesMapper.deleteBySubjectId(subjectId);
        return "redirect:/admin/basicInfo/subject/" + departmentId;
    }

    // -----------------------------------------------------
    @Resource
    private ClassesMapper classesMapper;

    @GetMapping("/classes/{id}")
    public String basicInfoClasses(@PathVariable("id") Integer subjectId, Model model) {
        Classes c = new Classes();
        c.setSubjectId(subjectId);
        List<Classes> classes = classesMapper.listByEntity(c);
        model.addAttribute("classes", classes);
        model.addAttribute("subjectId", subjectId);
        return "admin/basic_info/basicInfoList_classes";
    }

    @GetMapping(value = {"/addClasses/{subjectId}"})
    public String addClasses(@PathVariable("subjectId") Integer subjectId, Model model) {
        Subject subject = subjectMapper.getById(subjectId);
        model.addAttribute("subject", subject);
        return "admin/basic_info/classes_add";
    }

    @PostMapping(value = {"/addClasses/{subjectId}"})
    public String doAddClasses(@PathVariable("subjectId") Integer subjectId, Classes classes) {
        classesMapper.insert(classes);
        return "redirect:/admin/basicInfo/classes/" + subjectId;
    }

    @DeleteMapping(value = {"/editClasses/{classesId}"})
    public String doDeleteClasses(@PathVariable("classesId") Integer classId, Integer subjectId) {
        int i = classesMapper.deleteById(classId);
        return "redirect:/admin/basicInfo/classes/" + subjectId;
    }

}