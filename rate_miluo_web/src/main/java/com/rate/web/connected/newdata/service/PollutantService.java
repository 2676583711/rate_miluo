package com.rate.web.connected.newdata.service;

import com.rate.web.statement.entity.PolluteRealtime;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/4
 */
public interface PollutantService {
    List<PolluteRealtime> getPollutantNewestData(Map<String, Object> params);
}
