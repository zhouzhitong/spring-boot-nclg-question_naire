package com.nclg.query;

import com.nclg.vo.NaireTypeVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 10:30
 **/
@SpringBootTest
public class NaireTypeVoQueryTest {

    @Autowired
    private NaireTypeVoQuery query;

    @Test
    void findOneByNaireId(){
        NaireTypeVo oneByNaireId = query.findOneByNaireId(1L);
        System.out.println(oneByNaireId);
    }
}
