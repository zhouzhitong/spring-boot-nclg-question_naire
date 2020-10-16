package com.nclg.config.component;

import com.nclg.constant.SecurityConstant;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截
 * @author 周志通
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行之前
     * @param request Servlet 原生API
     * @param response Servlet 原生API
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityContextImpl securityContext = (SecurityContextImpl)request.getSession().getAttribute(SecurityConstant.SECURITY_USERNAME) ;
        if ( securityContext == null ){
            //未登录
            request.setAttribute("error","没有权限，请先登录");
            request.getRequestDispatcher("/admin/login").forward(request,response);
            return false ;
        }else {
            //已登陆，放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
