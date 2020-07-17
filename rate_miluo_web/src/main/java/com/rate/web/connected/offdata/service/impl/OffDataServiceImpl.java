package com.rate.web.connected.offdata.service.impl;

import com.rate.system.rate_system.utils.DateUtils;
import com.rate.web.connected.offdata.dao.OffDataDao;
import com.rate.web.connected.offdata.entity.OffDataVO;
import com.rate.web.connected.offdata.entity.TempEntity;
import com.rate.web.connected.offdata.service.OffDataService;
import com.rate.web.statement.dao.PolluteDao;
import com.rate.web.statement.util.TableSelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author chenshixue
 * @Date 2019/12/6
 */
@Service
public class OffDataServiceImpl implements OffDataService {

    @Autowired
    private OffDataDao offDataDao;
    @Autowired
    private PolluteDao polluteDao;
    private static final String PATTERN = "yyyy-MM-dd HH:mm:00";

    @Override
    public List<OffDataVO> getAirOffList(Map<String, Object> params) {
        List<OffDataVO> voList = new ArrayList<>();
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        long start = DateUtils.format(beginTime, PATTERN).getTime();
        long end = DateUtils.format(endTime, PATTERN).getTime();
        long maxMillis = end - start;

        List<TempEntity> statusList = offDataDao.findAirStatusList(params); // 所有空气站
        List<TempEntity> dataList = offDataDao.findAirDataList(params); // 有数据的站
        for (TempEntity temp : statusList) {
            List<Long> timeList = new ArrayList<>();
            List<Long> timeInterval = new ArrayList<>();
            OffDataVO vo = new OffDataVO();
            vo.setName(temp.getSiteName());
            if (temp.getSiteCode() == null) {
                voList.add(vo);
                continue;
            }
            vo.setEqumentId(temp.getSiteCode());
            vo.setVideoName(temp.getVideoName());
            String siteCategory = temp.getSiteCategory();
            if (temp.getQueryTime() == null) {
                vo.setStatus(1);  // 离线
            } else {
                vo.setStatus(0);  // 在线
            }
            // 选出该站点，并将时间排序
            if (dataList.size() > 0) {
                List<TempEntity> list = dataList.stream().filter(item -> item.getSiteCode().equals(temp.getSiteCode())).collect(Collectors.toList());
                if (list.size() > 0) {
                    timeList = list.stream().map(TempEntity::getQueryTime).map(Date::getTime).sorted().collect(Collectors.toList());
                }
            }
            // 如果该站点在查询时间段内有数据
            if (timeList.size() > 0) {
                long interval = 0;
                long timeType = "21".equalsIgnoreCase(siteCategory) ? 60000 : 3600000;
                int count = 0;
                long sum = 0;
                for (int i = 0; i < timeList.size(); i++) {
                    if (i == 0) {
                        interval = timeList.get(0) - start;
                    } else if (i == timeList.size()){
                        interval = end - timeList.get(i);
                    } else {
                        interval = timeList.get(i) - timeList.get(i - 1);
                    }
                    if (interval > timeType) {
                        count++;
                        sum += interval;
                        timeInterval.add(interval);
                    }
                }
                vo.setOffCount(count);
                vo.setOffTotalTime(sum);
                Optional<Long> optional = timeInterval.stream().reduce(Long::max);
                if (optional.isPresent()) {
                    vo.setOffMaxTime(optional.get());
                } else {
                    vo.setOffMaxTime(0L);
                }
            } else {
                vo.setOffCount(1);
                vo.setOffMaxTime(maxMillis);
                vo.setOffTotalTime(maxMillis);
            }
            voList.add(vo);
        }
        sortByCode(voList);
        return voList;
    }

    @Override
    public List<OffDataVO> getPolluteOffList(Map<String, Object> params) {
        List<OffDataVO> voList = new ArrayList<>();
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        long start = DateUtils.format(beginTime, PATTERN).getTime();
        long end = DateUtils.format(endTime, PATTERN).getTime();
        long maxMillis = end - start;

        List<String> signList = TableSelUtil.getTableSignListOfMonth(beginTime, endTime, PATTERN);
        String equmentIdsArr = (String) params.get("equmentIds");
        List<String> sqlList = new ArrayList<>();
        for (int i=0; i<signList.size(); i++) {
            String sql =" DISTINCT equment_id site_code,recording_time AS query_time" +
                    " FROM miluo_pollutant_realtime_" + signList.get(i) +
                    " WHERE recording_time >= '"+beginTime+"' AND recording_time<='"+endTime+"'" +
                    " AND data_type = 1 AND equment_id IN ("+equmentIdsArr+")";
            sqlList.add(sql);
        }
        // 用 UNION 拼接多张表查询
        String sqlAll = String.join("\nUNION\nSELECT ", sqlList);
        params.put("sqlAll", sqlAll);

        List<TempEntity> statusList = offDataDao.findPolluteStatusList(params);
        List<TempEntity> dataList = offDataDao.findPolluteDataList(params);
        for (TempEntity temp : statusList) {
            List<Long> timeList = new ArrayList<>();
            List<Long> timeInterval = new ArrayList<>();
            OffDataVO vo = new OffDataVO();
            vo.setName(temp.getSiteName());
            if (temp.getSiteCode() == null) {
                voList.add(vo);
                continue;
            }
            vo.setEqumentId(temp.getSiteCode());
            vo.setVideoName(temp.getVideoName());
            if (temp.getQueryTime() == null) {
                vo.setStatus(1);  // 离线
            } else {
                vo.setStatus(0);  // 在线
            }
            if (dataList.size() > 0) {
                List<TempEntity> list = dataList.stream().filter(item -> item.getSiteCode().equals(temp.getSiteCode())).collect(Collectors.toList());
                if (list.size() > 0) {
                    timeList = list.stream().map(TempEntity::getQueryTime).map(Date::getTime).sorted().collect(Collectors.toList());
                }
            }
            if (timeList.size() > 0) {
                long interval = 0;
                int count = 0;
                long sum = 0;
                for (int i = 0; i < timeList.size(); i++) {
                    if (i == 0) {
                        interval = timeList.get(0) - start;
                    } else if (i == timeList.size()) {
                        interval = end - timeList.get(i);
                    } else {
                        interval = timeList.get(i) - timeList.get(i - 1);
                    }
                    if (interval > 60000) {
                        count++;
                        sum += interval;
                        timeInterval.add(interval);
                    }
                }
                vo.setOffCount(count);
                vo.setOffTotalTime(sum);
                Optional<Long> optional = timeInterval.stream().reduce(Long::max);
                if (optional.isPresent()) {
                    vo.setOffMaxTime(optional.get());
                } else {
                    vo.setOffMaxTime(0L);
                }
            } else {
                vo.setOffCount(1);
                vo.setOffMaxTime(maxMillis);
                vo.setOffTotalTime(maxMillis);
            }
            voList.add(vo);
        }
        sortByCode(voList);
        return voList;
    }

    @Override
    public List<OffDataVO> getWaterPlantOffList(Map<String, Object> params) {
        List<OffDataVO> voList = new ArrayList<>();
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        long start = DateUtils.format(beginTime, PATTERN).getTime();
        long end = DateUtils.format(endTime, PATTERN).getTime();
        long maxMillis = end - start;

        List<TempEntity> statusList = offDataDao.findWaterPlantStatusList(params);
        List<TempEntity> dataList = offDataDao.findWaterPlantDataList(params);
        Date now = new Date();
        for (TempEntity temp : statusList) {
            List<Long> timeList = new ArrayList<>();
            List<Long> timeInterval = new ArrayList<>();
            OffDataVO vo = new OffDataVO();
            vo.setName(temp.getSiteName());
            if (temp.getSiteCode() == null) {
                voList.add(vo);
                continue;
            }
            vo.setEqumentId(temp.getSiteCode());
            vo.setVideoName(temp.getVideoName());

            // 对国祯污水厂默认20分内没数据，则为离线，判断基准为10分钟
            if (temp.getQueryTime() == null) {
                vo.setStatus(1);  // 离线
            } else {
                if (!temp.getSiteName().contains("国祯")) {
                    if ((now.getTime() - temp.getQueryTime().getTime()) > 10*60*60) {
                        vo.setStatus(1);
                    } else {
                        vo.setStatus(0);    // 在线
                    }
                } else {
                    vo.setStatus(0);
                }
            }
            // 选出该站点，并将时间排序
            if (dataList.size() > 0) {
                List<TempEntity> list = dataList.stream().filter(item -> item.getSiteCode().equals(temp.getSiteCode())).collect(Collectors.toList());
                if (list.size() > 0) {
                    timeList = list.stream().map(TempEntity::getQueryTime).map(Date::getTime).sorted().collect(Collectors.toList());
                }
            }
            // 如果该站点在查询时间段内有数据
            if (timeList.size() > 0) {
                long interval = 0;
                long timeType = temp.getSiteCode().equals("0152316D159016") ? 180000 : 60000;
                int count = 0;
                long sum = 0;
                for (int i = 0; i < timeList.size(); i++) {
                    if (i == 0) {
                        interval = timeList.get(0) - start;
                    } else if (i == timeList.size()){
                        interval = end - timeList.get(i);
                    } else {
                        interval = timeList.get(i) - timeList.get(i - 1);
                    }
                    if (interval > timeType) {
                        count++;
                        sum += interval;
                        timeInterval.add(interval);
                    }
                }
                vo.setOffCount(count);
                vo.setOffTotalTime(sum);
                Optional<Long> optional = timeInterval.stream().reduce(Long::max);
                if (optional.isPresent()) {
                    vo.setOffMaxTime(optional.get());
                } else {
                    vo.setOffMaxTime(0L);
                }
            } else {
                vo.setOffCount(1);
                vo.setOffMaxTime(maxMillis);
                vo.setOffTotalTime(maxMillis);
            }
            voList.add(vo);
        }
        sortByCode(voList);
        return voList;
    }

    @Override
    public List<OffDataVO> getWaterAutoOffList(Map<String, Object> params) {
        List<OffDataVO> voList = new ArrayList<>();
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        long start = DateUtils.format(beginTime, PATTERN).getTime();
        long end = DateUtils.format(endTime, PATTERN).getTime();
        long maxMillis = end - start;

        List<TempEntity> statusList = offDataDao.findWaterAutoStatusList(params);
        List<TempEntity> dataList = offDataDao.findWaterAutoDataList(params);
        for (TempEntity temp : statusList) {
            List<Long> timeList = new ArrayList<>();
            List<Long> timeInterval = new ArrayList<>();
            OffDataVO vo = new OffDataVO();
            vo.setName(temp.getSiteName());
            if (temp.getSiteCode() == null) {
                voList.add(vo);
                continue;
            }
            vo.setEqumentId(temp.getSiteCode());
            vo.setVideoName(temp.getVideoName());
            if (temp.getQueryTime() == null) {
                vo.setStatus(1);  // 离线
            } else {
                vo.setStatus(0);  // 在线
            }
            // 选出该站点，并将时间排序
            if (dataList.size() > 0) {
                List<TempEntity> list = dataList.stream().filter(item -> item.getSiteCode().equals(temp.getSiteCode())).collect(Collectors.toList());
                if (list.size() > 0) {
                    timeList = list.stream().map(TempEntity::getQueryTime).map(Date::getTime).sorted().collect(Collectors.toList());
                }
            }
            // 如果该站点在查询时间段内有数据
            if (timeList.size() > 0) {
                long interval = 0;
                int count = 0;
                long sum = 0;
                for (int i = 0; i < timeList.size(); i++) {
                    if (i == 0) {
                        interval = timeList.get(0) - start;
                    } else if (i == timeList.size()){
                        interval = end - timeList.get(i);
                    } else {
                        interval = timeList.get(i) - timeList.get(i - 1);
                    }
                    if (interval > 60000) {
                        count++;
                        sum += interval;
                        timeInterval.add(interval);
                    }
                }
                vo.setOffCount(count);
                vo.setOffTotalTime(sum);
                Optional<Long> optional = timeInterval.stream().reduce(Long::max);
                if (optional.isPresent()) {
                    vo.setOffMaxTime(optional.get());
                } else {
                    vo.setOffMaxTime(0L);
                }
            } else {
                vo.setOffCount(1);
                vo.setOffMaxTime(maxMillis);
                vo.setOffTotalTime(maxMillis);
            }
            voList.add(vo);
        }
        sortByCode(voList);
        return voList;
    }

    private void sortByCode(List<OffDataVO> voList) {
        Collections.sort(voList, new Comparator<OffDataVO>() {
            @Override
            public int compare(OffDataVO o1, OffDataVO o2) {
                if (o1.getEqumentId() == null) {
                    return 1;
                }
                return o1.getEqumentId().compareTo(o2.getEqumentId());
            }
        });
    }
}
