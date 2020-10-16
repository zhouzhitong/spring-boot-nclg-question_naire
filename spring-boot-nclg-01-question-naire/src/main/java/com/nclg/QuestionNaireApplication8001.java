package com.nclg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 8:08
 **/
@MapperScan(value = {"com.nclg.mapper", "com.nclg.query"})
@SpringBootApplication
@EnableWebSecurity
@EnableTransactionManagement
public class QuestionNaireApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(QuestionNaireApplication8001.class, args);
    }

}
