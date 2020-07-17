package com.rate.web.statistic.dao;

import com.rate.web.statistic.entity.*;
import com.rate.web.statistic.vo.ComprehensiveExponent;
import com.rate.web.statistic.vo.QualityCategory;
import com.rate.web.statistic.vo.RateVO;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/7/15 9:51
 **/
@SqlResource("audit.dayExamine")
@Repository
public interface DayExamineDao extends BaseMapper<DayExamine> {

    /**
     * 获取天数据
     *
     * @param params 日期范围 站点编号
     * @return com.rate.web.audit.entity.DayExamine
     * @author shuzhangyao
     * @date 2019/7/15 9:54
     **/
    DayExamine findDayDataByDateAndSiteCode(Map<String, Object> params);

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
     * 全区空气质量优良天数、优良率统计表列表分页数据
     *
     * @param pageQuery
     * @return
     * @author LiuYong
     */
    PageQuery<RateVO> choicenessStatisticsPageList(PageQuery<RateVO> pageQuery);

    /**
     * 全市空气质量综合指数报表列表分页数据
     *
     * @param pageQuery
     * @return
     * @author LiuYong
     */
    PageQuery<ComprehensiveExponent> comprehensivePageList(PageQuery<ComprehensiveExponent> pageQuery);

    /**
     * 根据站点编码和查询条件查询所有co数据
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<Double> getCosBySiteCode(Map<String, Object> params);

    /**
     * 根据站点编码和查询条件查询所有o3_8h数据
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<Double> getO3EightsBySiteCode(Map<String, Object> params);

    /**
     * 全区空气质量类别统计列表分页数据
     *
     * @param pageQuery
     * @return
     * @author LiuYong
     */
    PageQuery<QualityCategory> qualityCategoryPageList(PageQuery<QualityCategory> pageQuery);

    /**
     * 根据时间及参数类型
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<YcDailyStatement> queryListByParam(Map<String, Object> params);

    /**
     * 根据时间及参数类型查询区域数据
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<YcDailyStatement> queryListByParam1(Map<String, Object> params);

    /**
     * 全区首要污染物统计
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<PrimaryPollute> primaryPollutanList(Map<String, Object> params);

    /**
     * 全区空气质量优良天数、优良率统计表列表数据
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<RateVO> choicenessStatisticsList(Map<String, Object> params);

    /**
     * 全市空气质量综合指数报表列表数据
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<ComprehensiveExponent> comprehensiveList(Map<String, Object> params);

    /**
     * 空气质量类别统计列表数据
     *
     * @param params
     * @return
     * @author LiuYong
     */
    List<QualityCategory> qualityCategoryList(Map<String, Object> params);

    PageQuery<PrimaryDays> primaryList(PageQuery<PrimaryDays> pageQuery);

    PageQuery<PrimaryDays> primaryListByArea(PageQuery<PrimaryDays> pageQuery);

    PageQuery<ExceedDays> overproofListByArea(PageQuery<ExceedDays> pageQuery);

    PageQuery<ExceedDays> overproofList(PageQuery<ExceedDays> pageQuery);

    PageQuery<ExceedRate> overproofRateListByArea(PageQuery<ExceedRate> pageQuery);

    PageQuery<ExceedRate> overproofRateList(PageQuery<ExceedRate> pageQuery);

    PageQuery<Grade> gradeListByArea(PageQuery<Grade> pageQuery);

    PageQuery<Grade> gradeList(PageQuery<Grade> pageQuery);

    @SqlStatement(type = SqlStatementType.SELECT)
    List<PrimaryDays> primaryGraphicList(Map<String, Object> params);

    List<PrimaryDays> primaryGraphicListByArea(Map<String, Object> params);

    @SqlStatement(type = SqlStatementType.SELECT)
    List<PrimaryDays> overproofGraphicList(Map<String, Object> params);

    List<PrimaryDays> overproofGraphicListByArea(Map<String, Object> params);

    @SqlStatement(type = SqlStatementType.SELECT)
    List<PrimaryDays> primaryListToExcel(Map<String, Object> params);

    List<PrimaryDays> primaryListByAreaToExcel(Map<String, Object> params);

    List<ExceedRate> overproofRateListByAreaToExcel(Map<String, Object> params);

    @SqlStatement(type = SqlStatementType.SELECT)
    List<ExceedRate> overproofRateListToExcel(Map<String, Object> params);

    List<Grade> gradeListByAreaToExcel(Map<String, Object> params);

    @SqlStatement(type = SqlStatementType.SELECT)
    List<Grade> gradeListToExcel(Map<String, Object> params);

    List<AverageCompare> averageYearCompareList11(Map<String, Object> params);

    List<AverageCompare> averageYearCompareList21(Map<String, Object> params);
}
