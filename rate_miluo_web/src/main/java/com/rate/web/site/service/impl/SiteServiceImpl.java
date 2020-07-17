package com.rate.web.site.service.impl;

import com.rate.system.rate_system.dao.SitePowerDao;
import com.rate.system.rate_system.dao.UserRoleDao;
import com.rate.system.rate_system.entity.Tree;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.site.dao.SiteDao;
import com.rate.web.site.entity.Site;
import com.rate.web.site.entity.SitePower;
import com.rate.web.site.enums.RoleConstant;
import com.rate.web.site.enums.SiteTypeEnum;
import com.rate.web.site.service.SiteService;
import com.rate.web.site.vo.SiteVO;
import com.rate.web.utils.ChineseCharacterUtil;
import com.rate.web.vidicon.entity.Vidicon;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shuzhangyao
 * @date 2019/5/27 10:39
 **/
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteDao siteDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private SitePowerDao sitePowerDao;

    @Override
    public List<Site> findSiteList(Map<String, Object> params) {
        Long userId = (Long) params.get("userId");
        if (null == userId) {
            return null;
        }
        return siteDao.getSiteList(userId.intValue());
    }

    /**
     * 站点树
     *
     * @param params 参数
     * @return java.util.List<com.rate.web.site.entity.Site>
     * @author shuzhangyao
     * @date 2019/5/27 11:01
     **/
    @Override
    public Tree<Site> findAllSiteTree(Map<String, Object> params) {
        Long userId = (Long) params.get("userId");
        if (null == userId) {
            return null;
        }
        List<Site> siteAll;
        if (StringUtils.isNotBlank((String) params.get("type"))) {
            siteAll = siteDao.getSiteList(userId.intValue());
        } else {
            siteAll = siteDao.findSiteTree(params);
        }
        Set<String> types = new HashSet<>();
        List<Tree<Site>> treeList = new ArrayList<>();
        for (Site site : siteAll) {
            String type = site.getSiteType();
            types.add(site.getSiteType());
            Tree<Site> currTree = new Tree<>();
            currTree.setId(site.getSiteCode());
            currTree.setText(site.getName());
            currTree.setParentId("-" + type);
            treeList.add(currTree);
        }

        for (String type : types) {
            Tree<Site> currTree = new Tree<>();
            currTree.setId("-" + type);
            currTree.setText(SiteTypeEnum.getNameByCode(type));
            currTree.setParentId("0");
            treeList.add(currTree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        return build(treeList);
    }

    /**
     * 站点树
     *
     * @param params 参数
     * @return java.util.Map<java.lang.String, com.rate.system.rate_system.entity.Tree < com.rate.web.site.entity.Site>>
     * @author shuzhangyao
     * @date 2019/5/27 14:32
     **/
    @Override
    public Map<String, Tree<Site>> findSiteTreeByType(Map<String, Object> params) {
        Long userId = (Long) params.get("userId");
        if (null == userId) {
            return null;
        }
        List<Long> roleIds = userRoleDao.listRoleId(userId);
        Long roleId = roleIds.get(0);
        List<Site> siteAll;
        if (StringUtils.isBlank((String) params.get("type")) && (roleId == 78 || roleId == 79 || roleId == 81 || roleId == 82) ) {
            siteAll = siteDao.getSiteList(userId.intValue());
        } else {
            siteAll = siteDao.findSiteTree(params);
        }
        Set<String> types = new HashSet<>();
        HashMap<String, List<Tree<Site>>> treeMap = new HashMap<>();
        for (Site site : siteAll) {
            String type = site.getSiteType();
            types.add(site.getSiteType());
            Tree<Site> currTree = new Tree<>();
            currTree.setId(site.getId());
            currTree.setText(site.getName());
            currTree.setParentId("-" + type);
            if (treeMap.containsKey(type)) {
                treeMap.get(type).add(currTree);
            } else {
                List<Tree<Site>> currList = new LinkedList<>();
                currList.add(currTree);
                treeMap.put(type, currList);
            }
        }
        Map<String, Tree<Site>> res = new HashMap<>();
        for (String type : types) {
            Tree<Site> tree = new Tree<>();
            tree.setId("-" + type);
            tree.setParentId("");
            tree.setHasParent(false);
            tree.setChildren(true);
            tree.setChecked(true);
            tree.setChildren(treeMap.get(type));
//            tree.setText(SiteTypeEnum.getNameByCode(type));
            tree.setText("所有站点");
            Map<String, Object> state = new HashMap<>(2);
            state.put("opened", true);
            tree.setState(state);
            //防止空类型错
            String key = SiteTypeEnum.getNameByCode(type);
            if (StringUtils.isNotBlank(key) && !"Null".equals(key)) {
                res.put(key, tree);
            }
        }
        return res;
    }

    /**
     * 设备树，按站点类型分类
     * @param params
     * @Return  java.util.Map<java.lang.String,com.rate.system.rate_system.entity.Tree<com.rate.web.site.entity.Site>>
     * @Author  chenshixue
     * @Date    2020/3/24 14:28
     */
    @Override
    public Map<String, Tree<Vidicon>> findSiteVideoTreeByType(Map<String, Object> params) {
        Long userId = (Long) params.get("userId");
        if (null == userId) {
            return null;
        }
        List<Long> roleIds = userRoleDao.listRoleId(userId);
        Long roleId = roleIds.get(0);

        List<Site> siteAll;
        if (roleId == 78 || roleId == 79 || roleId == 81 || roleId == 82) {
            siteAll = siteDao.getSiteList(userId.intValue());
        } else {
            siteAll = siteDao.getAllSite();
        }
        List<Vidicon> list = siteDao.findVideoAllList(params);

        if (siteAll == null || list == null) {
            return null;
        }
        Set<String> types = new HashSet<>();
        HashMap<String, List<Tree<Vidicon>>> treeMap = new HashMap<>();


        for (Site site : siteAll) {
            String type = site.getSiteType();

            Tree<Vidicon> currTree = new Tree<>();
            List<Tree<Vidicon>> vidiconNodes = new LinkedList<>();
            for (Vidicon vidicon : list) {
                if (vidicon.getSiteId() == Integer.parseInt(site.getId())) {
                    types.add(type);
                    // 说明该站点有摄像头
                    Tree<Vidicon> vidiconNode = new Tree<>();
                    vidiconNode.setId(vidicon.getId().toString());
                    vidiconNode.setText(vidicon.getVidiconName());
                    vidiconNode.setParentId(vidicon.getSiteId().toString());
                    vidiconNode.setHasParent(true);
                    vidiconNodes.add(vidiconNode);
                }
            }
            if (vidiconNodes.size() == 0) {  // 站点没有摄像头
                continue;
            }
            // 有摄像头
            currTree.setId(site.getId());
            currTree.setText(site.getName());
            currTree.setParentId("-" + type);
            currTree.setChildren(vidiconNodes);

            if (treeMap.containsKey(type)) {
                treeMap.get(type).add(currTree);
            } else {
                List<Tree<Vidicon>> currList = new LinkedList<>();
                currList.add(currTree);
                treeMap.put(type, currList);
            }
        }
        Map<String, Tree<Vidicon>> res = new HashMap<>();
        for (String type : types) {
            Tree<Vidicon> tree = new Tree<>();
            tree.setId("-" + type);
            tree.setParentId("");
            tree.setHasParent(false);
            tree.setChildren(true);
            tree.setChildren(treeMap.get(type));
            tree.setText("所有监控");
            Map<String, Object> state = new HashMap<>(2);
            state.put("opened", true);
            tree.setState(state);
            //防止空类型错
            String key = SiteTypeEnum.getNameByCode(type);
            if (StringUtils.isNotBlank(key)) {
                res.put(key, tree);
            }
        }
        return res;
    }

    private <T> Tree<T> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        for (Tree<T> children : nodes) {
            String pid = children.getParentId();
            if (pid == null || "0".equals(pid)) {
                topNodes.add(children);
                continue;
            }
            for (Tree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setHasParent(true);
                    parent.setChildren(true);
                    continue;
                }
            }
        }

        Tree<T> root = new Tree<T>();
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
            Map<String, Object> state = new HashMap<>(2);
            state.put("opened", true);
            root.setState(state);
        } else {
            root.setId("-1");
            root.setParentId("");
            root.setHasParent(false);
            root.setChildren(true);
            root.setChecked(true);
            root.setChildren(topNodes);
            root.setText("所有站点");
            Map<String, Object> state = new HashMap<>(2);
            state.put("opened", true);
            root.setState(state);
        }
        return root;
    }

    private <T> Tree<T> build(List<Tree<T>> nodes, String title) {
        if (nodes == null) {
            return null;
        }
        Tree<T> root = new Tree<T>();
        root.setId("0");
        root.setParentId("");
        root.setHasParent(false);
        root.setChildren(true);
        root.setChecked(true);
        root.setChildren(nodes);
        root.setText(title);
        Map<String, Object> state = new HashMap<>(2);
        state.put("opened", true);
        root.setState(state);
        return root;
    }

    @Override
    public PageQuery<Site> getMicro(PageQuery<Site> params) {
        return siteDao.getMicro(params);
    }


    @Override
    public PageQuery<Site> getSiteListByPage(PageQuery<Site> params) {
        return siteDao.getSiteListByPage(params);
    }


    @Override
    public List<Site> getSiteList(Integer userId) {
        return siteDao.getSiteList(userId);
    }


    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 10:22
     **/
    @Override
    public PageQuery<Site> getWaterAutoStation(PageQuery<Site> params) {
        return siteDao.getWaterAutoStation(params);
    }


    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.site.entity.Site>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 14:43
     **/
    @Override
    public PageQuery<Site> getPolluteWaterStation(PageQuery<Site> params) {
        return siteDao.getPolluteWaterStation(params);
    }

    @Override
    public Map<String, Object> findSiteInfo(String siteCode) {
        Map<String, Object> info = siteDao.findSiteInfoById(siteCode);
        String type = SiteTypeEnum.getNameByCode((String) info.get("siteType"));
        info.put("type", type);
        return info;
    }

    @Override
    public Map<String, Object> findSiteInfoById(String siteId) {
        Map<String, Object> info = siteDao.findSiteInfoById(siteId);
        String type = SiteTypeEnum.getNameByCode((String) info.get("siteType"));
        info.put("type", type);
        return info;
    }

    @Override
    public List<String> querySiteByUser(String userId) {
        long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        Long roleId = userRoleDao.findRoleIdByUserId(id);
        if (Arrays.asList(RoleConstant.ROLEIDS).contains(roleId)) {
            return siteDao.querySiteByUser(id).stream().map(SitePower::getSiteCode).collect(Collectors.toList());
        } else {
            return sitePowerDao.getAllSiteId();
        }
    }

    @Override
    public List<String> querySiteByUserAndType(String userId, String type) {
        long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        Long roleId = userRoleDao.findRoleIdByUserId(id);
        if (Arrays.asList(RoleConstant.ROLEIDS).contains(roleId)) {
            return siteDao.querySiteByUserAndType(id, type).stream().map(SitePower::getSiteCode).collect(Collectors.toList());
        } else {
            return sitePowerDao.getAllSiteId();
        }
    }

    @Override
    public List<Site> getAllSite() {
        return siteDao.getAllSite();
    }

    @Override
    public List<Site> queryListByUserIdAndSiteType(String userId, String type) {
        return siteDao.queryListByUserIdAndSiteType(userId, type);
    }

    @Override
    public Map<String, Object> queryAirSiteInfo(String siteCode) {
        return siteDao.queryAirSiteInfo(siteCode);
    }

    @Override
    public Map<String, Object> querySiteList(String userId) {
        LocalDateTime now = LocalDateTime.now();
        String oldOneHour = now.plus(-1, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));

        List<SiteVO> sites;
        Long roleId = userRoleDao.findRoleIdByUserId(Long.parseLong(userId));
        if (Arrays.asList(RoleConstant.ROLEIDS).contains(roleId)) {
            sites = siteDao.queryListByUserId(userId, oldOneHour);
        } else {
            sites = siteDao.queryAllList(oldOneHour);
        }
        Map<String, Object> result = new HashMap<>(30);
        sites.forEach(site -> {
            String py = ChineseCharacterUtil.getUpperCase(site.getName(), false);
            if (result.containsKey(py)) {
                List<SiteVO> useSites = (List<SiteVO>) result.get(py);
                useSites.add(site);
                result.put(py, useSites);
            } else {
                List<SiteVO> useSites = new ArrayList();
                useSites.add(site);
                result.put(py, useSites);
            }
        });
        return result;
    }

    @Override
    public Tree<Site> findSiteSingleTree(Long userId, String siteType) {
        List<Site> siteAll = siteDao.getSiteListByType(userId, siteType);
        Tree<Site> top = new Tree<>();
        top.setId("000");
        top.setHasParent(false);
        top.setText("空气站点");
        Map<String, Object> state = new HashMap<>(2);
        state.put("opened", true);
        top.setState(state);
        for (Site site : siteAll) {
            Tree<Site> currTree = new Tree<>();
            currTree.setId(site.getId());
            currTree.setText(site.getName());
            currTree.setParentId("000");
            currTree.setChildren(false);
            top.getChildren().add(currTree);
        }
        return top;
    }
}
