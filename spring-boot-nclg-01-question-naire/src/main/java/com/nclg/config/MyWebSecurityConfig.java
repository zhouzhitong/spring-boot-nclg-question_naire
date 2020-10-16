package com.nclg.config;

import com.nclg.config.encoder.MyPasswordEncoder;
import com.nclg.service.LoginVoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 15:59
 **/
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1、定制请求的授权规则
        // 设置不需要授权的请求
        http.authorizeRequests().antMatchers("/admin/**").permitAll();

        http.formLogin()
                .usernameParameter("username").passwordParameter("password")
                .loginPage("/admin/login").loginProcessingUrl("/admin/loginCheck")
                .failureUrl("/admin/loginError").defaultSuccessUrl("/admin/loginSuccess");

        // 开启自动配置的注销功能(自定义注销功能)
        http.logout().logoutSuccessUrl("/admin/login")
                .and()
                .sessionManagement().maximumSessions(1)
                .and().and().csrf().disable();

    }

    @Resource
    private LoginVoService service;

    /**
     * 功能描述: <br>3、定义认证规则
     * <>
     *
     * @param auth 认证规则参数
     * @author 周志通
     * @date 2020/5/29
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new MyPasswordEncoder());
    }
}
