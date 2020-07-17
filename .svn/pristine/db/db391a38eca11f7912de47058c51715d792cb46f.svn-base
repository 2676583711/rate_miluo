package com.rate.web.api.dao;

import com.rate.web.api.entity.DailyWaterSite;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName DailyWaterSiteDao
 * @Author liuyong
 * @Date 2019/6/6 13:55
 * @Version 1.0
 **/
@Repository
@SqlResource("api/dailyWaterSite")
public interface DailyWaterSiteDao extends BaseMapper<DailyWaterSite> {

    /**
     * 获取水质自动站小时数据 通过站点编号 以及时间
     *
     * @param siteCodes 站点
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNum   起始
     * @param pageSize  每页数
     * @return
     * @author LiuYong
     */
    List<DailyWaterSite> queryListByCodeAndTime(@Param("siteCodes") List<String> siteCodes,
                                                @Param("startTime") String startTime,
                                                @Param("endTime") String endTime,
                                                @Param("pageNum") Integer pageNum,
                                                @Param("pageSize") Integer pageSize);
}
