package com.rate.web.connected.newdata.dao;

import com.rate.web.statement.entity.PolluteRealtime;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/4
 */
@SqlResource("connected.newdata.pollutant")
@Repository
public interface PollutantDao {
    List<PolluteRealtime> getPollutantNewestData(Map<String, Object> params);
}
