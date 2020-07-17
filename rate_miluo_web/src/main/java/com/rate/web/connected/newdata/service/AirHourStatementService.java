package com.rate.web.connected.newdata.service;

import com.rate.web.connected.newdata.entity.AirHourStatement;
import com.rate.web.dataInter.vo.AirVO;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

public interface AirHourStatementService {


    PageQuery<AirHourStatement> findDetails(PageQuery<AirHourStatement> params);



    List<AirVO> hourInter(String siteCode, String startTime, String endTime);

}







