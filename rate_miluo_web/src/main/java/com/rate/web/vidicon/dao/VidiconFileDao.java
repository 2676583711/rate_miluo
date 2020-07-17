package com.rate.web.vidicon.dao;

import com.rate.web.picManagement.controller.vo.VidiconFileVO;
import com.rate.web.vidicon.entity.VidiconFile;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;


/**
* @ClassName: VidiconFileDao
* @Description: 摄像头文件
* @author jiangya
* @date 2019年6月14日 下午4:00:39
*
*/
@SqlResource("vidicon.vidiconFile")
public interface VidiconFileDao extends BaseMapper<VidiconFile> {

	/** 
	 * getNewPrintscreenByVidiconId:获取摄像头最新一张截图  <br/> 
	 * @author jiangya 
	 * @param vidiconId
	 * @return 
	 */  
	VidiconFile getNewPrintscreenByVidiconId(@Param("vidiconId") Long vidiconId);


	PageQuery<VidiconFileVO> getScreenPicsByCondition(PageQuery<VidiconFileVO> paras);

	void removeByFileIds(@Param("ids") String[] ids);
}
