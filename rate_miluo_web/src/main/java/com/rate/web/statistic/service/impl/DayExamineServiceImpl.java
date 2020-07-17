package com.rate.web.statistic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.statistic.dao.DayExamineDao;
import com.rate.web.statistic.entity.*;
import com.rate.web.statistic.service.DayExamineService;
import com.rate.web.statistic.util.FormatEntity;
import com.rate.web.statistic.vo.QualityCategory;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/7/15 9:30
 **/
@Service
public class DayExamineServiceImpl implements DayExamineService {

    @Autowired
    private DayExamineDao dayExamineDao;


    @Override
    public DayExamine findDayDataByDateAndSiteCode(Map<String, Object> params) {
        return dayExamineDao.findDayDataByDateAndSiteCode(params);
    }

    @Override
    public List<PrimaryPollute> primaryPollutanList(Map<String, Object> params) {
        return dayExamineDao.primaryPollutanList(params);
    }

    @Override
    public PageQuery<QualityCategory> qualityCategoryPageList(PageQuery<QualityCategory> pageQuery) {
        return dayExamineDao.qualityCategoryPageList(pageQuery);
    }

    @Override
    public List<AverageCompare> averageYearCompareList(Map<String, Object> params) {
        String siteCodes = (String) params.get("siteCodes");
        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        return dayExamineDao.averageYearCompareList(params);
    }

    @Override
    public List<AverageCompare> averageYearCompareList1(Map<String, Object> params) {
        String siteCodes = (String) params.get("siteCodes");
        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        String examineType = (String) params.get("examineType");
        if (examineType.equals("0")){
            //原始
            return dayExamineDao.averageYearCompareList11(params);
        }else {
            return dayExamineDao.averageYearCompareList1(params);
        }

    }

    @Override
    public List<AverageCompare> averageYearCompareList2(Map<String, Object> params) {
        String siteCodes = (String) params.get("siteCodes");
        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        String examineType = (String) params.get("examineType");
        if (examineType.equals("0")){
            //原始
            return dayExamineDao.averageYearCompareList21(params);
        }else {
            return dayExamineDao.averageYearCompareList2(params);
        }

    }

    @Override
    public List<QualityCategory> qualityCategoryList(Map<String, Object> params) {
        return dayExamineDao.qualityCategoryList(params);
    }

    /**
     * 日数据审核数据
     * @param pageQuery
     * @Return  org.beetl.sql.core.engine.PageQuery<com.rate.web.dataquery.vo.PrimaryPollute>
     * @Author  chenshixue
     * @Date    2019/12/23 17:08
     */
    @Override
    public PageQuery<PrimaryDays> primaryList(PageQuery<PrimaryDays> pageQuery) {
        String type = (String)((LinkedHashMap) pageQuery.getParas()).get("stationType");
        if ("4".equals(type)) {  // 查区域
            return dayExamineDao.primaryListByArea(pageQuery);
        }
        return dayExamineDao.primaryList(pageQuery);
    }

    @Override
    public PageQuery<ExceedDays> overproofList(PageQuery<ExceedDays> pageQuery) {
        String type = (String)((LinkedHashMap) pageQuery.getParas()).get("stationType");
        if ("3".equals(type)) {
            return dayExamineDao.overproofListByArea(pageQuery);
        }
        return dayExamineDao.overproofList(pageQuery);
    }

    @Override
    public PageQuery<ExceedRate> overproofRateList(PageQuery<ExceedRate> pageQuery) {
        String type = (String)((LinkedHashMap) pageQuery.getParas()).get("stationType");
        if ("4".equals(type)) {
            return dayExamineDao.overproofRateListByArea(pageQuery);
        }
        return dayExamineDao.overproofRateList(pageQuery);
    }

    @Override
    public PageQuery<Grade> gradeList(PageQuery<Grade> pageQuery) {
        String type = (String)((LinkedHashMap) pageQuery.getParas()).get("stationType");
        if ("4".equals(type)) {
            return dayExamineDao.gradeListByArea(pageQuery);
        }
        return dayExamineDao.gradeList(pageQuery);
    }

    @Override
    public JSONObject primaryGraphicList(Map<String, Object> params) {
        JSONObject result = new JSONObject();
        String type = (String) params.get("stationType");
        String timeType = (String) params.get("timeType");
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        Map<String, List<PrimaryDays>> map = new LinkedHashMap<>();
        List<PrimaryDays> list;
        List<String> xItem = null;
        if ("4".equals(type)) {
            list = dayExamineDao.primaryGraphicListByArea(params);
        } else {
            list = dayExamineDao.primaryGraphicList(params);
        }
        try {
            if ("1".equals(timeType)) {
                xItem = DateUtils.getMonthBetween(startDate, endDate);
            } else if ("2".equals(timeType)) {
                xItem = FormatEntity.formatXItemBySeason(startDate, endDate);
            } else if ("3".equals(timeType)) {
                xItem = FormatEntity.formatXItemByYear(startDate, endDate);
            } else if ("4".equals(timeType)) {
                xItem = new ArrayList<>();
                xItem.add(list.get(0).getDataTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (PrimaryDays primary : list) {
            String dataTime = primary.getDataTime();
            if ("2".equals(timeType)) {
                primary.setDataTime(FormatEntity.formatSingleSeasonByYear(dataTime));
            }
            if (map.containsKey(primary.getSiteName())) {
                map.get(primary.getSiteName()).add(primary);
            } else {
                List<PrimaryDays> p = new ArrayList<>();
                p.add(primary);
                map.put(primary.getSiteName(), p);
            }
        }
        result.put("xItem", xItem);
        result.put("data", map);
        return result;
    }

    @Override
    public JSONObject overproofGraphicList(Map<String, Object> params) {
        JSONObject result = new com.alibaba.fastjson.JSONObject();
        String type = (String) params.get("stationType");
        String timeType = (String) params.get("timeType");
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        Map<String, List<PrimaryDays>> map = new LinkedHashMap<>();
        List<PrimaryDays> list;
        List<String> xItem = null;
        if ("4".equals(type)) {
            list = dayExamineDao.overproofGraphicListByArea(params);
        } else {
            list = dayExamineDao.overproofGraphicList(params);
        }
        try {
            if ("1".equals(timeType)) {
                xItem = DateUtils.getMonthBetween(startDate, endDate);
            } else if ("2".equals(timeType)) {
                xItem = FormatEntity.formatXItemBySeason(startDate, endDate);
            } else if ("3".equals(timeType)) {
                xItem = FormatEntity.formatXItemByYear(startDate, endDate);
            } else if ("4".equals(timeType)) {
                xItem = new ArrayList<>();
                xItem.add(list.get(0).getDataTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (PrimaryDays primary : list) {
            String dataTime = primary.getDataTime();
            if ("2".equals(timeType)) {
                primary.setDataTime(FormatEntity.formatSingleSeasonByYear(dataTime));
            }
            if (map.containsKey(primary.getSiteName())) {
                map.get(primary.getSiteName()).add(primary);
            } else {
                List<PrimaryDays> p = new ArrayList<>();
                p.add(primary);
                map.put(primary.getSiteName(), p);
            }
        }
        result.put("xItem", xItem);
        result.put("data", map);
        return result;
    }

    @Override
    public List<PrimaryDays> primaryList(Map<String, Object> params) {
        String type = (String)(params).get("stationType");
        if ("4".equals(type)) {
            return dayExamineDao.primaryListByAreaToExcel(params);
        }
        return dayExamineDao.primaryListToExcel(params);
    }

    @Override
    public List<ExceedRate> overproofRateList(Map<String, Object> params) {
        String type = (String) params.get("stationType");
        if ("4".equals(type)) {
            return dayExamineDao.overproofRateListByAreaToExcel(params);
        }
        return dayExamineDao.overproofRateListToExcel(params);
    }

    @Override
    public List<Grade> gradeList(Map<String, Object> params) {
        String type = (String) params.get("stationType");
        if ("4".equals(type)) {
            return dayExamineDao.gradeListByAreaToExcel(params);
        }
        return dayExamineDao.gradeListToExcel(params);
    }

    @Override
    public PageQuery<PrimaryPollute> primaryPollutePageList(PageQuery<PrimaryPollute> pageQuery) {
        return dayExamineDao.primaryPollutePageList(pageQuery);
    }

    @Override
    public boolean save(DayExamine dayExamine) {
        return dayExamineDao.updateTemplateById(dayExamine) > 0;
    }
}
