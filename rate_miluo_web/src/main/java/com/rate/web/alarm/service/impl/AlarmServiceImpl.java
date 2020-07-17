package com.rate.web.alarm.service.impl;

import com.rate.web.alarm.dao.AlarmDao;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.UserInfo;
import com.rate.web.alarm.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author chenshixue
 * @Date 2020/1/3
 */
@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmDao alarmDao;

    @Override
    public List<AlarmEntity> findByIds(List<Long> ids) {
        return alarmDao.createQuery().andIn("id", ids).select();
    }

    @Override
    public UserInfo findUserByEqumentId(String equipmentId) {
        return alarmDao.findUserByEqumentId(equipmentId);
    }
}
