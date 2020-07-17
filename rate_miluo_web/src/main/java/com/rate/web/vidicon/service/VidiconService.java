package com.rate.web.vidicon.service;

import com.rate.web.site.entity.Site;
import com.rate.web.vidicon.entity.Vidicon;

import java.util.List;
import java.util.Map;

/**
* @ClassName: VidiconService
* @Description: 摄像机
* @author jiangya
* @date 2019年6月1日 下午1:25:42
*
*/
public interface VidiconService {

	/** 
	 * getVidiconsBySiteId: 根据站点id获取摄像机<br/> 
	 * @author jiangya 
	 * @param siteId
	 * @return 
	 */  
	List<Vidicon> getVidiconsBySiteId(Integer siteId);

	/** 
	 * saveVidicon:保存摄像头信息 <br/> 
	 * @author jiangya 
	 * @param vidicon
	 * @return 
	 */  
	Long saveVidicon(Vidicon vidicon);

	/** 
	 * getVidiconsBySiteIdAndName: 根据站点和摄像头名称搜索<br/> 
	 * @author jiangya 
	 * @param siteId
	 * @param vidiconName
	 * @return 
	 */  
	List<Vidicon> getVidiconsBySiteIdAndName(Integer siteId, String vidiconName);

	/** 
	 * remove:删除 <br/> 
	 * @author jiangya 
	 * @param id
	 * @return 
	 */  
	int remove(Long id);

	/** 
	 * getVidiconByVidiconId:根据id获取,包括accessToken <br/> 
	 * @author jiangya 
	 * @param vidiconId
	 * @return 
	 */  
	Vidicon getVidiconByVidiconId(Long vidiconId);


	/**
	 * @Param [siteId]
	 * @Return java.util.List<com.rate.web.vidicon.entity.Vidicon>
	 * @author xiaoshi
	 * @Description
	 * @Date 2019/6/10
	 * @Time 11:03
	 **/

	List<Vidicon> getVidiconsById(String id);


    int updateVidicon(Vidicon vidicon);

    /**
     * 所有设备
     * @param params
     * @Return  java.util.List<com.rate.web.vidicon.entity.Vidicon>
     * @Author  chenshixue
     * @Date    2020/3/25 9:46
     */
    List<Vidicon> findVidiconList(Map<String, Object> params);

	List<Site> findSiteListByUserId(Long userId);
}
