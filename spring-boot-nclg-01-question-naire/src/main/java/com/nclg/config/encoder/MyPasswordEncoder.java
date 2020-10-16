package com.nclg.config.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 密码认证必须要的配置【在Spring Boot 2.x之后的 Spring Security 需要添加】
 * @author 周志通
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }

}
