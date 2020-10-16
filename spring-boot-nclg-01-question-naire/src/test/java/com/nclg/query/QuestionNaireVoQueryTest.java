package com.nclg.query;

import com.nclg.vo.NaireExamVo;
import com.nclg.vo.QuestionNaireVo;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 8:52
 **/
@SpringBootTest
public class QuestionNaireVoQueryTest {

    @Resource
    private QuestionNaireVoQuery questionNaireVoQuery;

    @Test
    void findAllTest(){

    }

    @Test
    void findOneTest(){
        List<NaireExamVo> oneByIdToGetAllInfo = questionNaireVoQuery.findOneByIdToGetAllInfo(1L);
        oneByIdToGetAllInfo.forEach(System.out::println);
    }

}
