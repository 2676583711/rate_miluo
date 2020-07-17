package com.rate.web.connected.newdata.service;

import com.rate.web.connected.newdata.entity.PolluteWaterMinutes;
import org.beetl.sql.core.engine.PageQuery;

public interface PolluteWaterMinutesService {

    PageQuery<PolluteWaterMinutes> waterMinutes(PageQuery<PolluteWaterMinutes>params);

}
