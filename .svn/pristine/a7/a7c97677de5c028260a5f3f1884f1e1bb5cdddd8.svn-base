package com.rate.web.alarm.monitor.service.impl;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.AlarmEntityEpx;
import com.rate.web.alarm.monitor.dao.ExceedDao;
import com.rate.web.alarm.monitor.entity.Exceed;
import com.rate.web.alarm.monitor.service.ExceedService;
import com.rate.web.api.constant.Constant;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExceedServiceImpl implements ExceedService {

    @Autowired
    private ExceedDao airExceedDao;
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 17:45
     **/
    @Override
    public PageQuery<Exceed> buttonAirAlarm(PageQuery<Exceed> params) {
        return airExceedDao.buttonAirAlarm(params);
    }
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 17:45
     **/
    @Override
    public PageQuery<AlarmEntity> airAlarmQuery(PageQuery<AlarmEntity> params) {
        return airExceedDao.airAlarmQuery(params);
    }
    @Override
    public List<AlarmEntity> airAlarmQueryExp(Map<String, Object> params) {
        return airExceedDao.airAlarmQueryExp(params);
    }
    /**
     * @Param [params, pageNum, pageSize]
     * @Return java.util.List<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 19:19
     **/
    @Override
    public List<Exceed> getAlertListByParams(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        if (params == null) {
            return null;
        }
       /* String siteCode = (String) params.get("siteCode");
        List<String> sites = new ArrayList<>();
        if (StringUtils.isNotBlank(siteCode)) {
            params.remove("siteType");
            sites.add(siteCode);
            params.put("siteCode", sites);
        } else {
            params.remove("siteCode");
        }*/
        //站点类型
        String siteType = (String)params.get("siteType");
        if (Constant.SITE_POLLUTE.equals(siteType)) {
            //涉水涉气污染源
            return airExceedDao.getAlertPollutantWater(params,pageNum,pageSize);
        }
        return airExceedDao.getAlertListByParams(params, pageNum, pageSize);
    }


    @Override
    public List<AlarmEntity> getAlertListByParams2(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        if (params == null) {
            return null;
        }

        return airExceedDao.getAlertListByParams2(params, pageNum, pageSize);
    }
    @Override
    public AlarmEntity findInfoById(Long id) {
        return airExceedDao.findInfoById(id);
    }

    @Override
    public List<AlarmEntityEpx> airAlarmQuerySituationExp(Map<String, Object> params) {
        return airExceedDao.airAlarmQuerySituationExp(params);
    }
}
