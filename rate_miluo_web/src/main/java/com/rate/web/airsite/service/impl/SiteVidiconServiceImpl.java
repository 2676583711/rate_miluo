package com.rate.web.airsite.service.impl;

import com.rate.web.airsite.dao.SiteVidiconDao;
import com.rate.web.airsite.entity.JianCeParam;
import com.rate.web.airsite.entity.SiteVidicon;
import com.rate.web.airsite.service.SiteVidiconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxx
 * @date 2019/6/14 10:32
 **/
@Service
public class SiteVidiconServiceImpl implements SiteVidiconService {

    @Autowired
    SiteVidiconDao siteVidiconDao;
    @Override
    public List<SiteVidicon> getListBySiteId(Integer id) {
        return siteVidiconDao.getListBySiteId(id);
    }

    @Override
    public SiteVidicon getByEquId(Long id) {
        return siteVidiconDao.single(id);
    }
}
