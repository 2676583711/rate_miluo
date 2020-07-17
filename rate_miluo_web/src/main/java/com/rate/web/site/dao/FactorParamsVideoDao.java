package com.rate.web.site.dao;

import com.rate.web.site.entity.FactorParamsVideo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author chenshixue
 * @Date 2019/12/16
 */
@Repository
public interface FactorParamsVideoDao extends BaseMapper<FactorParamsVideo> {
    @Sql(value=" delete from miluo_factor_params_video where video_id = ? ")
    void deleteByVideoId(@Param("videoId") String videoId);
}
