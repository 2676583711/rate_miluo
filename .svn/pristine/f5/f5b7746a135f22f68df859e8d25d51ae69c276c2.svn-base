package com.rate.web.site.dao;

import com.rate.web.site.entity.Site;
import com.rate.web.site.entity.SitePower;
import com.rate.web.site.vo.SiteVO;
import com.rate.web.vidicon.entity.Vidicon;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/27 10:39
 **/
@Repository
@SqlResource("site.site")
public interface SiteDao extends BaseMapper<Site> {

    /**
     * 站点树
     *
     * @param params 参数
     * @return java.util.List<com.rate.web.site.entity.Site>
     * @author shuzhangyao
     * @date 2019/5/27 11:01
     **/
    List<Site> findSiteTree(Map<String, Object> params);

    /**
     * 站点
     *
     * @param params 参数
     * @return java.util.List<com.rate.web.site.entity.Site>
     * @author shuzhangyao
     * @date 2019/5/28 18:18
     **/
    List<Site> findSiteList(Map<String, Object> params);

    /**
     * @Param [params] 参数
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description 查微型站点的站点
     * @Date 2019/5/31
     * @Time 13:38
     **/

    PageQuery<Site> getMicro(PageQuery<Site> params);


    PageQuery<Site> getSiteListByPage(PageQuery<Site> params);


    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 10:18
     **/

    List<Site> getSiteList(@Param("userId") Integer userId);


    PageQuery<Site> getWaterAutoStation(PageQuery<Site> params);


    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 14:43
     **/
    PageQuery<Site> getPolluteWaterStation(PageQuery<Site> params);

    /**
     * @Param []
     * @Return java.util.List<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 20:13
     **/

    List<Site> getAirList();
//    getPolluteWaterSites

    /**
     * @Param []
     * @Return java.util.List<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 23:00
     **/
    List<Site> getPolluteWaterSites();
//    getWaterSites

    /**
     * @Param []
     * @Return java.util.List<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 23:17
     **/
    List<Site> getWaterSites();

    Map<String, Object> findSiteInfo(@Param("siteCode") String siteCode);

    Map<String, Object> findSiteInfoById(@Param("siteId") String siteId);

    /**
     * 根据userId 和站点类型查询站点
     *
     * @param userId 用户编码
     * @param type   站点类型
     * @return
     * @Author LiuYong
     */
    List<Site> queryListByUserIdAndSiteType(@Param("userId") String userId,
                                            @Param("type") String type);


    /**
     * 查询空气站点
     *
     * @param siteCode 空气站点编码
     * @return
     * @author LiuYong
     */
    Map<String, Object> queryAirSiteInfo(@Param("siteCode") String siteCode);

    /**
     * app站点列表
     *
     * @param userId 用户ID
     * @param time 时间
     * @return
     */
    List<SiteVO> queryListByUserId(@Param("userId") String userId,
                                   @Param("time") String time);

    List<SiteVO> queryAllList(@Param("time") String time);


    /**
     * 查询该用户能查看的站点
     *
     * @param userId
     * @return
     * @author LiuYong
     */
    List<SitePower> querySiteByUser(@Param("userId") Long userId);

    /**
     * 根据类型查询该用户能查看的站点
     *
     * @param userId
     * @return
     * @author LiuYong
     */
    List<SitePower> querySiteByUserAndType(@Param("userId") Long userId,
                                           @Param("type") String type);

    List<Site> getAllSite();

    List<Vidicon> findVideoAllList(Map<String, Object> params);

    List<Site> getSiteListByType(@Param("userId") Long userId,
                                 @Param("siteType") String siteType);
}
