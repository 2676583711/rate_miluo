package com.rate.web.statistic.service;

import com.alibaba.fastjson.JSONObject;
import com.rate.web.statistic.entity.*;
import com.rate.web.statistic.vo.QualityCategory;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/7/15 9:30
 **/
public interface DayExamineService {

    /**
     * 获取天数据
     *
     * @param params 日期 yyyy-MM-dd 站点编号
     * @return com.rate.web.audit.entity.DayExamine
     * @author shuzhangyao
     * @date 2019/7/15 9:54
     **/
    DayExamine findDayDataByDateAndSiteCode(Map<String, Object> params);

    boolean save(DayExamine dayExamine);

    /**
     * 全区首要污染物统计分页审核数据
     *
     * @param pageQuery
     * @return
     * @author LiuYong
     */
    PageQuery<PrimaryPollute> primaryPollutePageList(PageQuery<PrimaryPollute> pageQuery);

    /**
     * 根据站点和时间条件,查询六项参数的平均浓度同比
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<AverageCompare> averageYearCompareList(Map<String, Object> params);

    List<AverageCompare> averageYearCompareList1(Map<String, Object> params);

    List<AverageCompare> averageYearCompareList2(Map<String, Object> params);

    /**
     * 全区空气质量类别统计列表分页数据
     *
     * @param pageQuery
     * @return
     * @author LiuYong
     */
    PageQuery<QualityCategory> qualityCategoryPageList(PageQuery<QualityCategory> pageQuery);

    /**
     * 全区首要污染物统计
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<PrimaryPollute> primaryPollutanList(Map<String, Object> params);

    /**
     * 空气质量类别统计列表数据
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<QualityCategory> qualityCategoryList(Map<String, Object> params);

    /**
     * 根据站点类型查询首要污染物统计
     * @param pageQuery
     * @Return  org.beetl.sql.core.engine.PageQuery<com.rate.web.dataquery.vo.PrimaryPollute>
     * @Author  chenshixue
     * @Date    2019/12/23 16:31
     */
    PageQuery<PrimaryDays> primaryList(PageQuery<PrimaryDays> pageQuery);

    /**
     * 污染物超标天数统计
     * @param pageQuery
     * @Return  org.beetl.sql.core.engine.PageQuery<com.rate.web.query.pollutant.entity.ExceedDays>
     * @Author  chenshixue
     * @Date    2019/12/24 9:37
     */
    PageQuery<ExceedDays> overproofList(PageQuery<ExceedDays> pageQuery);

    /**
     * 污染物超标率统计
     * @param pageQuery
     * @Return  org.beetl.sql.core.engine.PageQuery<com.rate.web.query.pollutant.entity.ExceedDays>
     * @Author  chenshixue
     * @Date    2019/12/24 9:37
     */
    PageQuery<ExceedRate> overproofRateList(PageQuery<ExceedRate> pageQuery);

    /**
     * 空气质量（等级分布）
     * @param pageQuery
     * @Return  org.beetl.sql.core.engine.PageQuery<com.rate.web.query.grade.entity.Grade>
     * @Author  chenshixue
     * @Date    2019/12/24 18:44
     */
    PageQuery<Grade> gradeList(PageQuery<Grade> pageQuery);

    /**
     * 首要污染物图形分析
     * @param params
     * @Return  com.alibaba.fastjson.JSONObject
     * @Author  chenshixue
     * @Date    2019/12/25 10:51
     */
    JSONObject primaryGraphicList(Map<String, Object> params);

    /**
     * 超标污染物图形分析
     * @param params
     * @Return  com.alibaba.fastjson.JSONObject
     * @Author  chenshixue
     * @Date    2019/12/25 10:51
     */
    JSONObject overproofGraphicList(Map<String, Object> params);

    List<PrimaryDays> primaryList(Map<String, Object> params);

    List<ExceedRate> overproofRateList(Map<String, Object> params);

    List<Grade> gradeList(Map<String, Object> params);
}
