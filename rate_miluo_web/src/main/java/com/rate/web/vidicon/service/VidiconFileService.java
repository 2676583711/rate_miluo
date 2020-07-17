package com.rate.web.vidicon.service;

import com.rate.web.picManagement.controller.vo.VidiconFileVO;
import com.rate.web.vidicon.entity.VidiconFile;
import org.beetl.sql.core.engine.PageQuery;

/**
* @ClassName: VidiconFileService
* @Description: 摄像头文件
* @author jiangya
* @date 2019年6月14日 下午4:01:49
*
*/
public interface VidiconFileService {

	/** 
	 * save: 保存<br/> 
	 * @author jiangya 
	 * @param vidiconFile 
	 */  
	void save(VidiconFile vidiconFile);

	/** 
	 * getNewPrintscreenByVidiconId:获取摄像头最新一张截图 <br/> 
	 * @author jiangya 
	 * @param vidiconId
	 * @return 
	 */  
	VidiconFile getNewPrintscreenByVidiconId(Long vidiconId);

	/**
	 * 分页查询指定条件内的截图
	 * @param paras
	 * @return
	 */
	PageQuery<VidiconFileVO> getScreenPicsByCondition(PageQuery<VidiconFileVO> paras);

	void removeByFileIds(String[] ids);
}
