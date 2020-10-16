package com.nclg.query;

import com.nclg.vo.ExamInfoVo;
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
 * @date 2020/9/20 14:44
 **/
@SpringBootTest
public class ExamInfoVoQueryTest {

    @Resource
    private ExamInfoVoQuery query;

    /**
     * 根据ID信息查找试题信息
     **/
    @Test
    void getOneById(){
        ExamInfoVo oneById = query.getOneById(1L);
        System.out.println(oneById);
    }

    /**
     * 查询所有试题信息
     */
    @Test
    void getAllExamInfoVo(){
        List<ExamInfoVo> allExamInfoVo = query.getAllExamInfoVo();
        allExamInfoVo.forEach(System.out::println);
    }

}
