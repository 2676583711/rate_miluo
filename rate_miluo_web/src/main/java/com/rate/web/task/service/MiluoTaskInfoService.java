package com.rate.web.task.service;

import org.beetl.sql.core.engine.PageQuery;

import com.rate.web.task.entity.MiluoTaskInfo;

import java.util.List;
import java.util.Map;

public interface MiluoTaskInfoService {

	/**
	 * <p>Title: list</p>  
	 * <p>Description:分页显示</p>  
	 * @param pageQuery
	 * @return
	 */
	PageQuery<MiluoTaskInfo> list(PageQuery<MiluoTaskInfo> pageQuery);

/**
 * @Param [miluoTaskInfo]
 * @Return int
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/3
 * @Time 20:14
 **/
	void save(MiluoTaskInfo miluoTaskInfo);

	/**
	
	 * <p>Title: historyList</p>  
	
	 * <p>Description: 历史任务</p>  
	
	 * @param pageQuery
	 * @return
	 */
	PageQuery<MiluoTaskInfo> historyList(PageQuery<MiluoTaskInfo> pageQuery);
	//删除待办任务
	int remove(String id);
	/**
	 * <p>Title: findMiluoTaskInfoById</p>  
	
	 * <p>Description: 查看详情</p>  
	
	 * @param id
	 * @return
	 */
	MiluoTaskInfo findMiluoTaskInfoById(String id,String status);
	/**
	 * 
	
	 * <p>Title: batchRemove</p>  
	
	 * <p>Description:批量删除待办任务 </p>  
	
	 * @param ids
	 * @return
	 */
	int batchRemove(String[] ids);

	/**
	 * <p>Title: updateTemplateById</p>  
	 * <p>Description: </p>  
	 * @param miluoTaskInfo
	 */
	void updateTemplateById(MiluoTaskInfo miluoTaskInfo);

	/**
	 *  分页根据关键字，任务状态查询任务信息
	 * @param params
	 * @ahthor LiuYong
	 * @return
	 */
	List<MiluoTaskInfo> queryTaskList(Map<String,Object> params);

	/**
	 *

	 * <p>Title: findRoleList</p>

	 * <p>Description: 通过用户id查所有的角色</p>

	 * @param userId
	 * @return
	 */
	List<Long> findRoleList(Long userId);
    /***
     * @Param [pageQuery]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.task.entity.MiluoTaskInfo>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/7/5
     * @Time 14:45
     **/
	PageQuery<MiluoTaskInfo> taskInfoCount(PageQuery<MiluoTaskInfo> pageQuery);

    /**
     * @Param [id]
     * @Return com.rate.web.task.entity.MiluoTaskInfo
     * @author xiaoshi
     * @Description
     * @Date 2019/7/8
     * @Time 11:24
     **/
	MiluoTaskInfo TaskInfoById(String id);

	List<Long> findUserIdsByRoleId(Long roleId);
}
