package com.rate.system.rate_system.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import com.rate.system.rate_system.entity.BbsUser;

@SqlResource("commons.bbsUser")
public interface BbsUserDao extends BaseMapper<BbsUser> {
	
		
		@SqlStatement(params="max")
		List<BbsUser> getScoreTop(Integer max);
		
		@SqlStatement(params="max")
		List<BbsUser> getLevelTop(Integer max);
}
