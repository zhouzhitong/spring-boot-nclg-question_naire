package com.nclg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 16:14
 **/
@Controller
@RequestMapping(value = "/admin")
public class LoginCheckController {


    @GetMapping(value = {"/loginError"})
    public String loginError(Model model) {
        model.addAttribute("error", "登录失败，请重新核对你的信息");
        return "admin/login";
    }

    /**
     * 登录成功【重定向，防止重复提交表单】
     * @return 返回成功登录页面 {@link com.nclg.config.MyWebMvcConfig}
     */
    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        return "redirect:/admin/main.html" ;
    }


}
