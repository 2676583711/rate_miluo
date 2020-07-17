package com.rate.web.site.service;

import com.rate.system.rate_system.entity.Tree;
import com.rate.web.site.entity.Site;
import com.rate.web.vidicon.entity.Vidicon;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/27 10:38
 **/
public interface SiteService {

    /**
     * 站点树
     *
     * @param params 参数
     * @return com.rate.system.rate_system.entity.Tree<com.rate.web.site.entity.Site>
     * @author shuzhangyao
     * @date 2019/5/27 11:01
     **/
    Tree<Site> findAllSiteTree(Map<String, Object> params);

    /**
     * 站点树
     *
     * @param params 参数
     * @return java.util.Map<java.lang.String, com.rate.system.rate_system.entity.Tree < com.rate.web.site.entity.Site>>
     * @author shuzhangyao
     * @date 2019/5/27 14:32
     **/
    Map<String, Tree<Site>> findSiteTreeByType(Map<String, Object> params);

    /**
     * @param params
     * @return java.util.List<com.rate.web.site.entity.Site>
     * @author shuzhangyao
     * @date 2019/5/28 18:17
     **/
    List<Site> findSiteList(Map<String, Object> params);

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description 查微型的站点
     * @Date 2019/5/31
     * @Time 14:24
     **/
    PageQuery<Site> getMicro(PageQuery<Site> params);


    PageQuery<Site> getSiteListByPage(PageQuery<Site> params);

    /**
     * @param userId
     * @return
     */
    List<Site> getSiteList(Integer userId);

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 11:06
     **/
    PageQuery<Site> getWaterAutoStation(PageQuery<Site> params);

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 14:44
     **/
    PageQuery<Site> getPolluteWaterStation(PageQuery<Site> params);

    /**
     * 单站查询
     *
     * @param siteCode 站点编号
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author shuzhangyao
     * @date 2019/6/4 16:48
     **/
    Map<String, Object> findSiteInfo(String siteCode);

    Map<String, Object> findSiteInfoById(String siteCode);

    /**
     * 根据userId 和站点类型查询站点
     *
     * @param userId 用户编码
     * @param type   站点类型
     * @return
     * @Author LiuYong
     */
    List<Site> queryListByUserIdAndSiteType(String userId, String type);

    /**
     * 查询空气站点
     *
     * @param siteCode 空气站点编码
     * @return
     * @author LiuYong
     */
    Map<String, Object> queryAirSiteInfo(String siteCode);

    /**
     * 查询该用户能查看的所有数据
     *
     * @param userId
     * @return 站点列表
     */
    Map<String, Object> querySiteList(String userId);


    /**
     * 查询该用户能查看的站点
     *
     * @param userId
     * @return
     * @author LiuYong
     */
    List<String> querySiteByUser(String userId);

    /**
     * 根据用户id和站点类型查看站点
     * @param userId
     * @param type
     * @Return  java.util.List<java.lang.String>
     * @Author  chenshixue
     * @Date    2019/11/27 9:49
     */
    List<String> querySiteByUserAndType(String userId, String type);

    List<Site> getAllSite();

    Map<String, Tree<Vidicon>> findSiteVideoTreeByType(Map<String, Object> params);

    Tree<Site> findSiteSingleTree(Long userId, String siteType);
}
