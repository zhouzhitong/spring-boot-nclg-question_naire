package com.nclg.service;

import com.nclg.vo.LoginVo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 描述：<br>用户登录业务
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 16:00
 **/
public interface LoginVoService extends UserDetailsService {

    /**
     * 用户登录检查
     * @author 周志通
     * @date 2020/6/24 19:58
     * @param username 用户名
     * @return {@link LoginVo}
     **/
    LoginVo findOneByUsername(String username) ;

}
