package com.rate.web.task.service.impl;

import com.rate.system.rate_system.dao.UserRoleDao;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.monitor.dao.ExceedDao;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.system.rate_system.dao.RoleDao;
import com.rate.web.task.dao.MiluoTaskInfoDao;
import com.rate.web.task.entity.MiluoTaskInfo;
import com.rate.web.task.service.MiluoTaskInfoService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.List;


@Service
public class MiluoTaskInfoServiceImpl implements MiluoTaskInfoService{

	@Autowired
	private MiluoTaskInfoDao miluoTaskInfoDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ExceedDao exceedDao;
	/**
	 * 分页
	 */
	@Override
	public PageQuery<MiluoTaskInfo> list(PageQuery<MiluoTaskInfo> pageQuery) {
		// TODO Auto-generated method stub
		return miluoTaskInfoDao.list(pageQuery);
	}
	
	/**
	 * 历史任务分页
	 */
	@Override
	public PageQuery<MiluoTaskInfo> historyList(PageQuery<MiluoTaskInfo> pageQuery) {
		// TODO Auto-generated method stub
		return miluoTaskInfoDao.historyList(pageQuery);
	}

	
	@Override
	public int remove(String id) {
		// TODO Auto-generated method stub
		return miluoTaskInfoDao.deleteById(id);
	}

	/**
	 * 通过id查看当前记录
	 */
	@Override
	public MiluoTaskInfo  findMiluoTaskInfoById(String id,String status) {
		return miluoTaskInfoDao.findMiluoTaskInfoById(id,status);
	}
	/**
	 * 批量删除待办任务
	 */
	@Transactional
	@Override
	public int batchRemove(String[] ids) {
		int count = 0;
		for(int i=0;i<ids.length;i++) {
			miluoTaskInfoDao.deleteById(ids[i]);
			count++;
		}
		return count;
	}

	/**
	 * 保存任务下发操作，并修改报警信息表状态
	 * @param miluoTaskInfo
	 * @Return  void
	 * @Author  chenshixue
	 * @Date    2019/12/20 19:08
	 */
	@Transactional
	@Override
	public void save(MiluoTaskInfo miluoTaskInfo) {
		//新增 生成状态(未完成) 生成任务编号(任务类型+时间戳)
		miluoTaskInfo.setTaskNo(parseTaskType(miluoTaskInfo.getTaskType())
				+ new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		miluoTaskInfo.setStatus(0); // 默认是未完成
		miluoTaskInfoDao.insertTemplate(miluoTaskInfo,true);
		if (miluoTaskInfo.getAlarmId() != null) {
			AlarmEntity alarmEntity = new AlarmEntity();
			alarmEntity.setStatus(2);  // 任务已下发
			alarmEntity.setId(miluoTaskInfo.getAlarmId());
			exceedDao.updateTemplateById(alarmEntity);
		}
	}

	public String parseTaskType(String type) {
		switch (type) {
			case "0":
				return "BJ";
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
				return "YW";
		}
		return "QT";
	}

	
	/**
	 * 修改任务列表
	 */
	@Override
	public void updateTemplateById(MiluoTaskInfo miluoTaskInfo) {
		miluoTaskInfoDao.updateById(miluoTaskInfo);
	}


	@Override
	public List<MiluoTaskInfo> queryTaskList(Map<String, Object> params) {
		return miluoTaskInfoDao.queryTaskList(params);
	}


	@Override
	public List<Long> findRoleList(Long userId) {
		return roleDao.findRoleList(userId);
	}
    /*
     * @Param [pageQuery]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.task.entity.MiluoTaskInfo>
     * @author xiaoshi
     * @Description
     * @Date 2019/7/6
     * @Time 18:00
     **/
	@Override
	public PageQuery<MiluoTaskInfo> taskInfoCount(PageQuery<MiluoTaskInfo> pageQuery) {
		return miluoTaskInfoDao.taskInfoCount(pageQuery);
	}
   /**
    * @Param [id]
    * @Return com.rate.web.task.entity.MiluoTaskInfo
    * @author xiaoshi
    * @Description
    * @Date 2019/7/8
    * @Time 11:24
    **/
	@Override
	public MiluoTaskInfo TaskInfoById(String id) {
		return miluoTaskInfoDao.TaskInfoById(id);
	}

	/**
	 * 根据角色id查询所有用户id
	 * @param roleId
	 * @Return  java.util.List<java.lang.Long>
	 * @Author  chenshixue
	 * @Date    2019/7/30 10:07
	 */
	@Override
	public List<Long> findUserIdsByRoleId(Long roleId) {
		return userRoleDao.findUserIdsByRoleId(roleId);
	}


}
