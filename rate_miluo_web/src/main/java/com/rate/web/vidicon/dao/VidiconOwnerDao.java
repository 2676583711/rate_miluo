package com.rate.web.vidicon.dao;

import com.rate.web.vidicon.entity.VidiconOwner;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
* @ClassName: VidiconOwnerDao
* @Description: 摄像头所有人
* @author jiangya
* @date 2019年6月4日 上午11:29:22
*
*/
@Repository
@SqlResource("vidicon.vidicon")
public interface VidiconOwnerDao extends BaseMapper<VidiconOwner> {


}
