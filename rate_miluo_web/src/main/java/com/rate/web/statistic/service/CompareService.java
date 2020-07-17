package com.rate.web.statistic.service;

import com.rate.web.statistic.entity.AverageCompare;

import java.util.List;
import java.util.Map;

public interface CompareService {
    List<AverageCompare> averageCompareList(Map<String, Object> params);

    List<AverageCompare> averageCompareListByExamine(Map<String, Object> params);
}