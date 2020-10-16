package com.nclg.util;

import com.nclg.entity.LoginRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/6/24 20:02
 **/
public class RoleSplitUtils {

    /**
     * 功能描述：<br> 用于存储角色信息
     * @param roles 角色信息
     * @return 角色对象
     */
    public static List<GrantedAuthority> getRole(List<LoginRole> roles){
        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (LoginRole role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole().trim())) ;
        }
        return authorities ;
    }

}
