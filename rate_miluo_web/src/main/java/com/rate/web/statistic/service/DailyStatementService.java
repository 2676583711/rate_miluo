package com.rate.web.statistic.service;

import com.alibaba.fastjson.JSONObject;
import com.rate.web.statistic.entity.Grade;
import com.rate.web.statistic.entity.PrimaryDays;
import com.rate.web.statistic.vo.AirStationInfoVo;
import com.rate.web.statistic.vo.QualityCategory;
import com.rate.web.statistic.vo.RankVo;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * @Author LiuYong
 * @Date 2019/6/24 17:50
 * @Version 1.0
 **/
public interface DailyStatementService {

    PageQuery<QualityCategory> qualityCategoryPageList(PageQuery<QualityCategory> pageQuery);

    PageQuery<QualityCategory> qualityCategoryPageList2(PageQuery<QualityCategory> pageQuery);

    PageQuery<RankVo> selRankVo(PageQuery<RankVo> pageQuery, Map<String, Object> params);

    PageQuery<PrimaryDays> primaryList(PageQuery<PrimaryDays> pageQuery);

    JSONObject primaryGraphicList(Map<String, Object> params);

    PageQuery<Grade> gradeList(PageQuery<Grade> pageQuery);

    List<PrimaryDays> primaryList(Map<String, Object> params);

    List<Grade> gradeList(Map<String, Object> params);

    List<AirStationInfoVo> getStaionInfos();
}
