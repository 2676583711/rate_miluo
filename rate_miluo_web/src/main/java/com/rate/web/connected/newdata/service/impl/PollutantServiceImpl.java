package com.rate.web.connected.newdata.service.impl;

import com.rate.web.connected.newdata.dao.PollutantDao;
import com.rate.web.connected.newdata.service.PollutantService;
import com.rate.web.statement.entity.PolluteRealtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/4
 */
@Service
public class PollutantServiceImpl implements PollutantService {

    @Autowired
    PollutantDao pollutantDao;

    @Override
    public List<PolluteRealtime> getPollutantNewestData(Map<String, Object> params) {
        return pollutantDao.getPollutantNewestData(params);
    }
}
