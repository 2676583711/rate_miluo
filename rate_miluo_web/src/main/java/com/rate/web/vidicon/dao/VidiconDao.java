package com.rate.web.vidicon.dao;

import com.rate.web.site.entity.Site;
import com.rate.web.vidicon.entity.Vidicon;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @ClassName: VidiconDao
* @Description: 摄像机
* @author jiangya
* @date 2019年6月1日 下午1:25:22
*
*/
@Repository
@SqlResource("vidicon.vidicon")
public interface VidiconDao extends BaseMapper<Vidicon> {

	/** 
	 * getVidiconByVidiconId:根据id获取,包括accessToken <br/> 
	 * @author jiangya 
	 * @param vidiconId
	 * @return 
	 */  
	Vidicon getVidiconByVidiconId(@Param("vidiconId") Long vidiconId);

    List<Vidicon> getVidiconsBySiteId(@Param("siteId") Integer siteId);

    /**
     * @Param [siteId]
     * @Return java.util.List<com.rate.web.vidicon.entity.Vidicon>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 11:03
     **/

	List<Vidicon> getVidiconsById(@Param("id") String id);


    List<Vidicon> findVidiconList(Map<String, Object> params);

	List<Site> findSiteListByUserId(@Param("userId") Long userId);
}
