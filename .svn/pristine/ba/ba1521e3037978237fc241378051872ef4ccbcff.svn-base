package com.rate.web.vidicon.service.impl;

import com.rate.system.rate_system.service.RoleService;
import com.rate.web.site.entity.Site;
import com.rate.web.site.service.SiteService;
import com.rate.web.vidicon.dao.VidiconDao;
import com.rate.web.vidicon.entity.Vidicon;
import com.rate.web.vidicon.service.VidiconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @ClassName: VidiconServiceImpl
* @Description: 摄像机
* @author jiangya
* @date 2019年6月1日 下午1:25:36
*
*/
@Service
public class VidiconServiceImpl implements VidiconService {
	@Autowired
	private VidiconDao vidiconDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SiteService siteService;
	/* 
	* <p>Title: getVidiconsBySiteId</p>
	* <p>Description:根据站点id查找 </p>
	* @param siteId
	* @return
	* @see com.rate.web.vidicon.service.VidiconService#getVidiconsBySiteId(java.lang.Integer)
	*/
	@Override
	public List<Vidicon> getVidiconsBySiteId(Integer siteId) {
//		return vidiconDao.createQuery().andEq("site_id", siteId).select();
		return vidiconDao.getVidiconsBySiteId(siteId);
	}

	/* 
	* <p>Title: saveVidicon</p>
	* <p>Description: 保存摄像头信息</p>
	* @param vidicon
	* @return
	* @see com.rate.web.vidicon.service.VidiconService#saveVidicon(com.rate.web.vidicon.entity.Vidicon)
	*/
	@Override
	public Long saveVidicon(Vidicon vidicon) {
		return vidiconDao.insertReturnKey(vidicon).getLong();
	}

	/* 
	* <p>Title: getVidiconsBySiteIdAndName</p>
	* <p>Description: 根据站点和摄像头名称搜索</p>
	* @param siteId
	* @param vidiconName
	* @return
	* @see com.rate.web.vidicon.service.VidiconService#getVidiconsBySiteIdAndName(java.lang.Integer, java.lang.String)
	*/
	@Override
	public List<Vidicon> getVidiconsBySiteIdAndName(Integer siteId, String vidiconName) {
		return vidiconDao.createQuery().andEq("site_id", siteId).andLike("vidicon_name", "%"+vidiconName+"%").select();
	}

	/* 
	* <p>Title: remove</p>
	* <p>Description: 删除</p>
	* @param id
	* @return
	* @see com.rate.web.vidicon.service.VidiconService#remove(java.lang.Long)
	*/
	@Override
	public int remove(Long id) {
		return vidiconDao.deleteById(id);
	}

	/* 
	* <p>Title: getVidiconByVidiconId</p>
	* <p>Description: 根据id获取,包括accessToken</p>
	* @param vidiconId
	* @return
	* @see com.rate.web.vidicon.service.VidiconService#getVidiconByVidiconId(java.lang.Long)
	*/
	@Override
	public Vidicon getVidiconByVidiconId(Long vidiconId) {
		return vidiconDao.getVidiconByVidiconId(vidiconId);
	}

	@Override
	public List<Vidicon> getVidiconsById(String id) {
		return vidiconDao.getVidiconsById(id);
	}

	@Override
	public int updateVidicon(Vidicon vidicon) {
		return vidiconDao.updateTemplateById(vidicon);
	}

	@Override
	public List<Vidicon> findVidiconList(Map<String, Object> params) {
		return vidiconDao.findVidiconList(params);
	}

	@Override
	public List<Site> findSiteListByUserId(Long userId) {
		Long roleId = roleService.getRoleIdByUserId(userId);
		if (roleId == 78 || roleId == 79 || roleId == 81 || roleId == 82) {
			return vidiconDao.findSiteListByUserId(userId);
		}
		return siteService.getAllSite();
	}


}
