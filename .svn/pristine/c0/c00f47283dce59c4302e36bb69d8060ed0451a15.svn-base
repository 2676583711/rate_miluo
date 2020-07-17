package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.FileDO;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@SqlResource("commons.sysFile")
public interface FileDao extends BaseMapper<FileDO> {
	public int count(Map<String, Object> map);
	public int batchRemove(@Param("ids") String[] ids);
	public List<FileDO> list(Map<String, Object> map);

	List<FileDO> getListByIds(@Param("ids") String[] ids);
	void deleteByIds(@Param("ids") String[] ids);
}
