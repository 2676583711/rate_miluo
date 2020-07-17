package com.rate.web.watercalcute.dao;

import com.rate.web.watercalcute.entity.WaterCalauteSort;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * @program: rate_hbsg_water
 * @description: 上报设置
 * @author: chenh
 * @create: 2020-04-02 14:48
 **/
@SqlResource("watercalcute.waterCalauteSort")
public interface WaterCalauteSortDao extends BaseMapper<WaterCalauteSort> {
    //水质类别计算
    Integer waterSort(@Param("paramName") String paramName, @Param("dataValue") Double dataValue);

}
