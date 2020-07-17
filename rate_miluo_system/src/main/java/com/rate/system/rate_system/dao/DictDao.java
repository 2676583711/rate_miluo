package com.rate.system.rate_system.dao;


import com.rate.system.rate_system.entity.Dict;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@SqlResource("commons.sysDict")
public interface DictDao extends BaseMapper<Dict>{
	PageQuery<Dict> queryByCondition(PageQuery<Dict> paras);

	Dict get(@Param("id") Long id);

	List<Dict> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(@Param("dict") Dict dict);

	int update(@Param("dict") Dict dict);

	int remove(@Param("id") Long id);

	int batchRemove(@Param("ids") Long[] ids);

	List<Dict> listType();

	List<String> getUionsType();

	List<Dict> getTypes(@Param("code") String code);
}
