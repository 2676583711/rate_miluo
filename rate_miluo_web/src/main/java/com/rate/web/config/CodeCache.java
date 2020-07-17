package com.rate.web.config;

import com.rate.web.watercalcute.dao.WaterCalauteSortDao;
import com.rate.web.watercalcute.entity.WaterCalauteSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: rate_hbsg_water
 * @ClassName: CodeCache
 * @description: 全局缓存
 * @author: chenh
 * @create: 2020-04-13 13:03
 **/
@Component
public class CodeCache {
    //缓存水质因子的所有数据
    public static Map<String, WaterCalauteSort> codeMap = new HashMap<String, WaterCalauteSort>();

    @Autowired
    private WaterCalauteSortDao waterCalauteSortDao;

    @PostConstruct
    public void init() {
        //系统启动中。。。加载codeMap
        List<WaterCalauteSort> waterCalauteSortList = waterCalauteSortDao.all();
        for (WaterCalauteSort waterCalauteSort : waterCalauteSortList) {
            codeMap.put(waterCalauteSort.getId(),waterCalauteSort);
        }
    }
    @PreDestroy
    public void destroy() {
        //系统运行结束
        codeMap.clear();
    }
}