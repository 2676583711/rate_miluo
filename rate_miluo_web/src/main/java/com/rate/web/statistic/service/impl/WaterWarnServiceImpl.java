package com.rate.web.statistic.service.impl;

import com.rate.web.desktop.entity.MapMarker;
import com.rate.web.statistic.dao.WaterWarnDao;
import com.rate.web.statistic.service.WaterWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: rate_miluo_parent
 * @ClassName: WaterWarnServiceImpl
 * @description:
 * @author: chenh
 * @create: 2020-07-16 11:51
 **/
@Service
public class WaterWarnServiceImpl implements WaterWarnService {

    @Autowired
    private WaterWarnDao waterWarnDao;

    @Override
    public List<MapMarker> findAllWaterWarn(String dateTime) {
        return waterWarnDao.findAllWaterWarn(dateTime);
    }
}
