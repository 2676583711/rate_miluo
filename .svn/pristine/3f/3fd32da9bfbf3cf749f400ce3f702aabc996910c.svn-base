package com.rate.web.vidicon.service.impl;

import com.rate.web.picManagement.controller.vo.VidiconFileVO;
import com.rate.web.vidicon.dao.VidiconFileDao;
import com.rate.web.vidicon.entity.VidiconFile;
import com.rate.web.vidicon.service.VidiconFileService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: VidiconFileServiceImpl
 * @Description: 摄像头文件
 * @author jiangya
 * @date 2019年6月14日 下午4:03:06
 *
 */
@Service
public class VidiconFileServiceImpl implements VidiconFileService {
	@Autowired
	VidiconFileDao vidiconFileDao;

	@Override
	public void save(VidiconFile vidiconFile) {
		vidiconFileDao.insertTemplate(vidiconFile);
	}

	@Override
	public VidiconFile getNewPrintscreenByVidiconId(Long vidiconId) {
		return vidiconFileDao.getNewPrintscreenByVidiconId(vidiconId);
	}

	@Override
	public PageQuery<VidiconFileVO> getScreenPicsByCondition(PageQuery<VidiconFileVO> paras) {
		return vidiconFileDao.getScreenPicsByCondition(paras);
	}

	@Override
	public void removeByFileIds(String[] ids) {
		vidiconFileDao.removeByFileIds(ids);
	}

}
