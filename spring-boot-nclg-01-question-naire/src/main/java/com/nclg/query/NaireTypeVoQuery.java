package com.nclg.query;

import com.nclg.vo.NaireTypeVo;

/**
 * 描述：<br>问卷 类型 的相关操作
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/9/20 10:15
 **/
public interface NaireTypeVoQuery {

    /**
     * 查询：根据ID信息查询
     * @param id
     * @return
     */
    NaireTypeVo findOneByNaireId(Long id);

}
