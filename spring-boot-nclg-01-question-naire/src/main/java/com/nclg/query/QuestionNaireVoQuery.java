package com.nclg.query;

import com.nclg.vo.NaireExamVo;

import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 8:41
 **/
public interface QuestionNaireVoQuery {


    /**
     * 查询试卷的具体信息
     * @param id
     * @return
     */
    List<NaireExamVo> findOneByIdToGetAllInfo(Long id);

}
