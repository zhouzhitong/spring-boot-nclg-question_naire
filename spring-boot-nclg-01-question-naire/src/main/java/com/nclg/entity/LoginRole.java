package com.nclg.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/6 12:58
 **/
@Data
public class LoginRole implements Serializable {

    /**
     * 主键：bigint
     */
    private Long id;
    /**
     * 外键【login.id】：long
     */
    private Long userId;
    /**
     * 角色信息：varchar[50]
     */
    private String role;
    /**
     * 备注信息：varchar[255]
     */
    private String remarks;

}
