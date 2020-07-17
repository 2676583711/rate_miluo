package com.rate.web.airsite.service;

import com.rate.web.airsite.entity.AirSite;
import com.rate.web.airsite.entity.PollutantFactory;
import org.beetl.sql.core.engine.PageQuery;

/**
 * @author xxx
 * @date 2019/5/27 16:12
 **/
public interface AirSiteService {

  public PageQuery<AirSite> getListByQueryCondition(PageQuery<AirSite> params);


  public  void  saveSite(AirSite airSite);

  public  void  remove(Integer id);

  public  void  updateSite(AirSite airSite);

  public  void  saveSite(PollutantFactory pollutantFactory);


  public  void  batchRemove(Integer[] ids);

   public AirSite findById(String id);
}
