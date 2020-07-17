package com.rate.web.vidicon.service.impl;

import com.rate.web.vidicon.dao.VidiconOwnerDao;
import com.rate.web.vidicon.entity.VidiconOwner;
import com.rate.web.vidicon.service.VidiconOwnerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: VidiconOwnerServiceImpl
 * @Description: 摄像头所有人
 * @author jiangya
 * @date 2019年6月4日 上午11:31:27
 *
 */
@Service
public class VidiconOwnerServiceImpl implements VidiconOwnerService {
	@Autowired
	VidiconOwnerDao vidiconOwnerDao;

	@Override
	public List<VidiconOwner> getAllVidiconOwner() {
		return vidiconOwnerDao.all();
	}

	@Override
	public int update(VidiconOwner vidiconOwner) {
		return vidiconOwnerDao.updateTemplateById(vidiconOwner);
	}

}
