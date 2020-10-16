package com.nclg.query;

import com.nclg.vo.LoginVo;

/**
 * 描述：<br>登录用户 的操作
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 12:59
 **/
public interface LoginVoQuery {

    /**
     * 通过用户名[username] 查询信息
     *
     * @param username 用户名信息
     * @return {@link LoginVo}
     **/
    LoginVo findOneByUsername(String username);

}
