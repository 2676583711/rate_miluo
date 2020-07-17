package com.rate.web.graphic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.graphic.dao.GraphicAirDao;
import com.rate.web.graphic.entity.AirDayStatements;
import com.rate.web.graphic.entity.AirHourStatements;
import com.rate.web.graphic.service.GraphicAirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shuzhangyao
 * @date 2019/5/30 19:13
 **/
@Service
public class GraphicAirServiceImpl implements GraphicAirService {

    @Autowired
    private GraphicAirDao graphicAirDao;

    /**
     * 获取最新小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/30 19:10
     **/
    @Override
    public List<AirHourStatements> findLatestData(Map<String, Object> params){
        return graphicAirDao.findStandardLatestData(params);
    }
    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    @Override
    public JSONObject findHourData(Map<String, Object> params){
        List<AirHourStatements> all = graphicAirDao.findHourData(params);
        Map<String, List<AirHourStatements>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            AirHourStatements hour = all.get(i);
            String siteName = hour.getStationName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(hour);
            }else{
                List<AirHourStatements> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
            x.add(sdf.format(hour.getQueryTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xDate", xs);
        return obj;
    }
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 11:57
     **/
    @Override
    public JSONObject findDayData(Map<String, Object> params){
        List<AirDayStatements> all = graphicAirDao.findDayData(params);
        Map<String, List<AirDayStatements>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            AirDayStatements day = all.get(i);
            String siteName = day.getStationName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(day);
            }else{
                List<AirDayStatements> list = new ArrayList<>();
                list.add(day);
                result.put(siteName, list);
            }
            x.add(sdf.format(day.getQueryTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xDate", xs);
        return obj;
    }

    /**
     *
     * @param param 站点编号 时间(yyyy-MM-dd)
     * @return java.util.List<com.rate.grid.web.TimedTask.entity.HourStatement>
     * @author shuzhangyao
     * @date 2019/5/31 13:57
     **/
    @Override
    public JSONObject findWindData(Map<String, Object> param) {
        List<AirHourStatements> all = graphicAirDao.findWindData(param);
        Map<String, List<AirHourStatements>> result = new HashMap<>();
        for (AirHourStatements hour : all) {
            String siteName = hour.getStationName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if (result.containsKey(siteName)) {
                if (hour.getWd() != null || hour.getWs() != null) {
                    result.get(siteName).add(hour);
                }
            } else {
                List<AirHourStatements> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
        }
        JSONObject obj = new JSONObject();
        obj.put("wds", windDirection(result));
        obj.put("data", result);
        return obj;
    }
    /**
     *
     * @param param 站点编号 时间(yyyy-MM-dd)
     * @return java.util.List<com.rate.grid.web.TimedTask.entity.HourStatement>
     * @author shuzhangyao
     * @date 2019/5/31 13:57
     **/
    @Override
    public JSONObject findChangeData(Map<String, Object> param) {
        List<AirHourStatements> all = graphicAirDao.findChangeData(param);
        JSONObject result = new JSONObject();
        for (AirHourStatements hour: all){
            String siteName = hour.getStationName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                ((List<AirHourStatements>)result.get(siteName)).add(hour);
            }else{
                List<AirHourStatements> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
        }
        return result;
    }

    /**
     * 获取当年到现在污染程度级别天数
     * @param params 站点编号 起始时间
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author shuzhangyao
     * @date 2019/6/4 18:08
     **/
    @Override
    public List<Map<String, Object>> findYearLevelDay(Map<String, Object> params) {
        return graphicAirDao.findYearLevelDay(params);
    }

    private JSONObject windDirection(Map<String, List<AirHourStatements>> data){
        Set<String> keys = data.keySet();
        JSONObject result = new JSONObject();
        s:for(String key : keys){
            Map<String ,List> map = new HashMap<>();
            List<AirHourStatements> siteData = data.get(key);
            if(siteData==null||siteData.size()==0){
                continue;
            }
            List<String> wds = new ArrayList<>();
            List<String> wss = new ArrayList<>();
            int count = 0;
            double n=0,nne=0,ne=0,ene=0,e=0,ese=0,se=0,sse=0,s=0,ssw=0,sw=0,wsw=0,w=0,wnw=0,nw=0,nnw=0;
            double ns=0,nnes=0,nes=0,enes=0,es=0,eses=0,ses=0,sses=0,ss=0,ssws=0,sws=0,wsws=0,ws=0,wnws=0,nws=0,nnws=0;
            for(AirHourStatements siteItem : siteData){
                String wdS = siteItem.getWd();
                String wsS = siteItem.getWs();
                if(StringUtils.isBlank(wdS) || StringUtils.isBlank(wsS)){
                    continue;
                }
                Double wd = Double.parseDouble(wdS);
                Double wsNum = Double.parseDouble(wsS);
                if(wd==null){
                    continue s;
                }
                if(wd>348.75||0>wd&&wd<=11.25){
                    n++;
                    if(wsNum!=null){
                        ns += wsNum;
                    }
                    count++;
                }else if(11.25<wd&&wd<=33.75){
                    nne++;
                    if(wsNum!=null){
                        nnes += wsNum;
                    }
                    count++;
                }else if(33.75<wd&&wd<=56.25){
                    ne++;
                    if(wsNum!=null){
                        nes += wsNum;
                    }
                    count++;
                }else if(56.25<wd&&wd<=78.75){
                    ene++;
                    if(wsNum!=null){
                        enes += wsNum;
                    }
                    count++;
                }else if(78.75<wd&&wd<=101.25){
                    e++;
                    if(wsNum!=null){
                        es += wsNum;
                    }
                    count++;
                }else if(101.25<wd&&wd<=123.75){
                    ese++;
                    if(wsNum!=null){
                        eses += wsNum;
                    }
                    count++;
                }else if(123.75<wd&&wd<=146.25){
                    se++;
                    if(wsNum!=null){
                        ses += wsNum;
                    }
                    count++;
                }else if(146.25<wd&&wd<=168.75){
                    sse++;
                    if(wsNum!=null){
                        sses += wsNum;
                    }
                    count++;
                }else if(168.75<wd&&wd<=191.25){
                    s++;
                    if(wsNum!=null){
                        ss += wsNum;
                    }
                    count++;
                }else if(191.25<wd&&wd<=213.75){
                    ssw++;
                    if(wsNum!=null){
                        ssws += wsNum;
                    }
                    count++;
                }else if(213.75<wd&&wd<=236.25){
                    sw++;
                    if(wsNum!=null){
                        sws += wsNum;
                    }
                    count++;
                }else if(236.25<wd&&wd<=258.75){
                    wsw++;
                    if(wsNum!=null){
                        wsws += wsNum;
                    }
                    count++;
                }else if(258.75<wd&&wd<=281.25){
                    w++;
                    if(wsNum!=null){
                        ws += wsNum;
                    }
                    count++;
                }else if(281.25<wd&&wd<=303.75){
                    wnw++;
                    if(wsNum!=null){
                        wnws += wsNum;
                    }
                    count++;
                }else if(303.75<wd&&wd<=326.25){
                    nw++;
                    if(wsNum!=null){
                        nws += wsNum;
                    }
                    count++;
                }else if(326.25<wd&&wd<=348.75){
                    nnw++;
                    if(wsNum!=null){
                        nnws += wsNum;
                    }
                    count++;
                }
            }
            if(count==0){
                continue ;
            }
            wds.add(0, formatWD((n/count)*100)+"");
            wds.add(1, formatWD((nne/count)*100)+"");
            wds.add(2, formatWD((ne/count)*100)+"");
            wds.add(3, formatWD((ene/count)*100)+"");
            wds.add(4, formatWD((e/count)*100)+"");
            wds.add(5, formatWD((ese/count)*100)+"");
            wds.add(6, formatWD((se/count)*100)+"");
            wds.add(7, formatWD((sse/count)*100)+"");
            wds.add(8, formatWD((s/count)*100)+"");
            wds.add(9, formatWD((ssw/count)*100)+"");
            wds.add(10, formatWD((sw/count)*100)+"");
            wds.add(11, formatWD((wsw/count)*100)+"");
            wds.add(12, formatWD((w/count)*100)+"");
            wds.add(13, formatWD((wnw/count)*100)+"");
            wds.add(14, formatWD((nw/count)*100)+"");
            wds.add(15, formatWD((nnw/count)*100)+"");

            wss.add(0, formatWD((ns/count))+"");
            wss.add(1, formatWD((nnes/count))+"");
            wss.add(2, formatWD((nes/count))+"");
            wss.add(3, formatWD((enes/count))+"");
            wss.add(4, formatWD((es/count))+"");
            wss.add(5, formatWD((eses/count))+"");
            wss.add(6, formatWD((ses/count))+"");
            wss.add(7, formatWD((sses/count))+"");
            wss.add(8, formatWD((ss/count))+"");
            wss.add(9, formatWD((ssws/count))+"");
            wss.add(10, formatWD((sws/count))+"");
            wss.add(11, formatWD((wsws/count))+"");
            wss.add(12, formatWD((ws/count))+"");
            wss.add(13, formatWD((wnws/count))+"");
            wss.add(14, formatWD((nws/count))+"");
            wss.add(15, formatWD((nnws/count))+"");
            map.put("wds", wds);
            map.put("wss", wss);
            result.put(key, map);
        }
        return result;
    }

    private double formatWD(double wd){
        BigDecimal b = null;
        try{
            b = new BigDecimal(wd);
        } catch (NumberFormatException e) {
            System.out.println(wd);
            e.printStackTrace();
        }
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
