package com.rate.web.airsite.dao;

import com.rate.web.airsite.entity.JianCeParam;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 15:13
 **/
@SqlResource("airSite.JianCeParam")
public interface JianCeParamDao extends BaseMapper<JianCeParam> {

    List<JianCeParam> getJianCeParamList();


    List<JianCeParam> getParamListByIds(@Param("ids") String[] ids);
}
