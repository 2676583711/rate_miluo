package com.rate.web.task.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.web.task.entity.MiluoTaskInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SqlResource("task.miluotaskinfo.miluotaskinfo")
public interface MiluoTaskInfoDao extends BaseMapper<MiluoTaskInfo> {

	/**
	 * <p>Title: list</p>  
	
	 * <p>Description: </p>  
	
	 * @param pageQuery
	 * @return
	 */
	PageQuery<MiluoTaskInfo> list(PageQuery<MiluoTaskInfo> pageQuery);
	List<MiluoTaskInfo> listExp(Map<String,Object> map);
	List<MiluoTaskInfo> historyListExp(Map<String,Object> map);

	/**
	 * <p>Title: historyList</p>  
	
	 * <p>Description: </p>  
	
	 * @param pageQuery
	 * @return
	 */
	PageQuery<MiluoTaskInfo> historyList(PageQuery<MiluoTaskInfo> pageQuery);
/**

 * <p>Title: findMiluoTaskInfoById</p>  

 * <p>Description: </p>  

 * @param id
 * @return
 */
	MiluoTaskInfo findMiluoTaskInfoById(@Param("id")String id,@Param("status")String status);

	/**
	 *  分页根据关键字，任务状态查询任务信息
	 * @param params
	 * @ahthor LiuYong
	 * @return
	 */
	List<MiluoTaskInfo> queryTaskList(Map<String,Object> params);

    /**
     * @Param [pageQuery]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.task.entity.MiluoTaskInfo>
     * @author xiaoshi
     * @Description
     * @Date 2019/7/5
     * @Time 11:03
     **/

	PageQuery<MiluoTaskInfo> taskInfoCount(PageQuery<MiluoTaskInfo> pageQuery);

	/**
	 * @Param
	 * @Return
	 * @author xiaoshi
	 * @Description
	 * @Date 2019/7/8
	 * @Time 11:21
	 **/
	MiluoTaskInfo TaskInfoById(@Param("id") String id);

}
