package com.rate.web.alarm.dao;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.UserInfo;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author chenshixue
 * @Date 2020/1/3
 */
@Repository
public interface AlarmDao extends BaseMapper<AlarmEntity> {

    @Sql(value = "SELECT s.`name` siteName,s.fzr,u.name,u.mobile,u.mgr_id FROM miluo_site s LEFT JOIN sys_user u ON u.user_id = s.fzr \n" +
            "WHERE s.id = (SELECT site_id FROM miluo_video WHERE equment_id = ?)")
    UserInfo findUserByEqumentId(String equipmentId);
}
