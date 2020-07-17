package com.rate.web.timetask.dao;

import com.rate.web.timetask.entity.StatementType;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ProjectName: rate_miluo_parent
 * @Package: com.rate.web.timetask.dao
 * @ClassName: StatementTypeDao
 * @author: xiaoshi
 * @Description: ${description}
 * @Date: 2019/6/4 14:46
 * @Version: 1.0
 */
@SqlResource("timetask.miluotaskinfo")
public interface StatementTypeDao extends BaseMapper<StatementType> {
    
/**
 * @Param []
 * @Return java.util.List<com.rate.web.timetask.entity.StatementType>
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/4
 * @Time 15:46
 **/
   List<StatementType> getSiteId();

}
