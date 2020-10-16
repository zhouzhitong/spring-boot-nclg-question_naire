package com.nclg.config;

import com.nclg.config.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 15:56
 **/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/").setViewName("admin/index");
        registry.addViewController("/admin/index").setViewName("admin/index");
        registry.addViewController("/admin/main.html").setViewName("admin/index");

        registry.addViewController("/admin/login").setViewName("admin/login");

        registry.addViewController("/admin/paper/paper_add").setViewName("admin/paper/paper_add");
    }

    /**
     * 注册登陆拦截器
     * @param registry {@link InterceptorRegistry}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/", "/admin/index"
                        , "/admin/login", "/admin/loginError"
                        // 静态资源
                        , "/webjars/**", "classpath:/static/**","classpath:static/**" ,"/asserts/**");
    }

}
