package com.nclg.query;

import com.nclg.vo.LoginVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 8:18
 **/
@SpringBootTest
public class LoginVoQueryTest {

    @Resource
    private LoginVoQuery query ;

    @Test
    void findOneByUserName(){
        String username = "root";
        LoginVo oneByUsername = query.findOneByUsername(username);
        System.out.println(oneByUsername);
    }

}
