package com.nclg.service.impl;

import com.nclg.logger.Logger;
import com.nclg.query.LoginVoQuery;
import com.nclg.service.LoginVoService;
import com.nclg.util.RoleSplitUtils;
import com.nclg.vo.LoginVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 16:01
 **/
@Service
public class LoginVoServiceImpl implements LoginVoService {

    @Resource
    private LoginVoQuery loginVoMapper;

    @Override
    public LoginVo findOneByUsername(String username) {
        return loginVoMapper.findOneByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询信息
        LoginVo loginVo = findOneByUsername(username);
        Logger.logger(LoginVoServiceImpl.class, loginVo != null ? loginVo.toString() : "查无此人");
        // 查到相关信息
        User user = null;
        if (loginVo != null) {
            List<GrantedAuthority> authorities = RoleSplitUtils.getRole(loginVo.getLoginRoles());
            user = new User(loginVo.getUsername(), loginVo.getPassword(), authorities);
        }
        return user;
    }
}
