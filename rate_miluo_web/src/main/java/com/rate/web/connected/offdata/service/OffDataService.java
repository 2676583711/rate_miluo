package com.rate.web.connected.offdata.service;

import com.rate.web.connected.offdata.entity.OffDataVO;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/6
 */
public interface OffDataService {
    List<OffDataVO> getAirOffList(Map<String, Object> params);

    List<OffDataVO> getPolluteOffList(Map<String, Object> params);

    List<OffDataVO> getWaterPlantOffList(Map<String, Object> params);

    List<OffDataVO> getWaterAutoOffList(Map<String, Object> params);
}
