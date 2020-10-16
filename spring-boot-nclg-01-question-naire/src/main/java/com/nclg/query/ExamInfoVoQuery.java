package com.nclg.query;

import com.nclg.vo.ExamInfoVo;

import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 12:57
 **/
public interface ExamInfoVoQuery {

    /**
     * 根据ID信息查找试题信息
     * @param id 主键ID
     * @return {@link ExamInfoVo}
     **/
    ExamInfoVo getOneById(Long id) ;

    /**
     * 查询所有试题信息
     * @return {@link List <ExamInfoVo>}
     */
    List<ExamInfoVo> getAllExamInfoVo() ;

}
