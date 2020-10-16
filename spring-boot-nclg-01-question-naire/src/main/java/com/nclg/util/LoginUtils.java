package com.nclg.util;

import com.nclg.constant.SecurityConstant;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020-6-5 15:43
 **/
public class LoginUtils {

    /**
     * 获取登录对象
     *
     * @param request Servlet原生API
     * @return {@link }
     * @author 周志通
     * @date 2020/6/5 15:45
     **/
    public static UserDetails getUserDetails(HttpServletRequest request) {
        SecurityContextImpl securityContext = getSecurityContextImpl(request);
        return securityContext != null ? ((UserDetails) securityContext.getAuthentication().getPrincipal()) : null;
    }

    /**
     * 获取登录对象
     *
     * @param session Servlet原生API
     * @return {@link UserDetails}
     * @author 周志通
     * @date 2020/6/5 15:46
     **/
    public static UserDetails getUserDetails(HttpSession session) {
        SecurityContextImpl securityContext = getSecurityContextImpl(session);
        return securityContext != null ? ((UserDetails) securityContext.getAuthentication().getPrincipal()) : null;
    }

    /**
     * 获取登录 的用户名
     *
     * @param request Servlet原生API
     * @return {@link String }
     */
    public static String getUseName(HttpServletRequest request) {
        SecurityContextImpl securityContext = getSecurityContextImpl(request);
        if (securityContext != null) {
            UserDetails userDetails = ((UserDetails) securityContext.getAuthentication().getPrincipal());
            return userDetails.getUsername();
        }
        return null;
    }

    /**
     * 获取登录 的用户名
     *
     * @param session Servlet原生API
     * @return {@link String }
     */
    public static String getUseName(HttpSession session) {
        SecurityContextImpl securityContext = getSecurityContextImpl(session);
        if (securityContext != null) {
            UserDetails userDetails = ((UserDetails) securityContext.getAuthentication().getPrincipal());
            return userDetails.getUsername();
        }
        return null;
    }

    private static SecurityContextImpl getSecurityContextImpl(HttpServletRequest request) {
        return (SecurityContextImpl) request.getSession().getAttribute(SecurityConstant.SECURITY_USERNAME);
    }

    private static SecurityContextImpl getSecurityContextImpl(HttpSession session) {
        return (SecurityContextImpl) session.getAttribute(SecurityConstant.SECURITY_USERNAME);
    }
}
