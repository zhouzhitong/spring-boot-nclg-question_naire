package com.nclg.vo;


import com.nclg.entity.LoginRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：<br>实体类【用于查询】： 登录信息的
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 12:59
 **/
@Data
public class LoginVo implements Serializable {

    /**
     * 主键：bigint
     */
    private Long id;
    /**
     * 用户名：varchar[20]
     */
    private String username;
    /**
     * 密码：varchar[20]
     */
    private String password;
    /**
     * 备注信息：varchar[255]
     */
    private String remarks;
    /**
     * 角色信息的集合
     */
    private List<LoginRole> loginRoles;

}
