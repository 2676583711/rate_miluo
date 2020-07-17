package com.rate.web.statistic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.web.statistic.dao.DailyStatementDao;
import com.rate.web.statistic.dto.Px;
import com.rate.web.statistic.entity.AverageCompare;
import com.rate.web.statistic.service.DailyStatementService;
import com.rate.web.statistic.util.FormatEntity;
import com.rate.web.statistic.entity.Grade;
import com.rate.web.statistic.entity.PrimaryDays;
import com.rate.web.statistic.vo.AirStationInfoVo;
import com.rate.web.statistic.vo.QualityCategory;
import com.rate.web.statistic.vo.RankVo;
import com.rate.web.utils.Tutil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

/**
 * @ClassName DailyStatementServiceImpl
 * @Author LiuYong
 * @Date 2019/6/24 17:53
 * @Version 1.0
 **/
@Service
public class DailyStatementServiceImpl implements DailyStatementService {

    @Autowired
    private DailyStatementDao dailyStatementDao;

    @Override
    public PageQuery<QualityCategory> qualityCategoryPageList(PageQuery<QualityCategory> pageQuery) {
        Map<String, Object> paras = (Map<String, Object>) pageQuery.getParas();
        String dateType = String.valueOf(paras.get("dateType"));
        PageQuery<QualityCategory> qualityCategoryPageQuery = dailyStatementDao.qualityCategoryPageList(pageQuery);
        List<QualityCategory> list = qualityCategoryPageQuery.getList();
        for (QualityCategory qualityCategory : list) {
            if ("1".equals(dateType)) {

            } else if ("2".equals(dateType)) {
                String times = qualityCategory.getTimes();
                int i = getjiduDays(times);
                qualityCategory.setSumDays(i);
            } else if ("3".equals(dateType)) {
                String times = qualityCategory.getTimes();
                int year = getYear(times);
                if (year == 2) {
                    qualityCategory.setSumDays(366);
                } else {
                    qualityCategory.setSumDays(365);
                }
            } else if ("4".equals(dateType)) {
                qualityCategory.setSumDays(qualityCategory.getAllsumDays());
            }
        }
        return qualityCategoryPageQuery;
    }

    @Override
    public PageQuery<QualityCategory> qualityCategoryPageList2(PageQuery<QualityCategory> pageQuery) {
        Map<String, Object> paras = (Map<String, Object>) pageQuery.getParas();
        String dateType = String.valueOf(paras.get("dateType"));
        PageQuery<QualityCategory> qualityCategoryPageQuery = dailyStatementDao.qualityCategoryPageList2(pageQuery);
        List<QualityCategory> list = qualityCategoryPageQuery.getList();
        for (QualityCategory qualityCategory : list) {
            if ("1".equals(dateType)) {

            } else if ("2".equals(dateType)) {
                String times = qualityCategory.getTimes();
                int i = getjiduDays(times);
                qualityCategory.setSumDays(i);
            } else if ("3".equals(dateType)) {
                String times = qualityCategory.getTimes();
                int year = getYear(times);
                if (year == 2) {
                    qualityCategory.setSumDays(366);
                } else {
                    qualityCategory.setSumDays(365);
                }
            } else if ("4".equals(dateType)) {
                qualityCategory.setSumDays(qualityCategory.getAllsumDays());
            }
        }
        return qualityCategoryPageQuery;
    }

    @Override
    public PageQuery<RankVo> selRankVo(PageQuery<RankVo> pageQuery, Map<String, Object> params) {
        PageQuery<RankVo> paglist = dailyStatementDao.selRankVo(pageQuery);
        List<RankVo> nowlist = paglist.getList();
        if (nowlist == null || nowlist.size() < 1) {
            return null;
        }
        for (RankVo rankVo : nowlist) {

            if (rankVo.getPm10() != null) {
                Double pm10 = Double.valueOf(rankVo.getPm10());
                double pm102 = getint(pm10, 0);
                rankVo.setPm10(String.valueOf((int) pm102));
                double pm10Zh = getint(pm102 / 70, 2);
                rankVo.setPm10Zh(pm10Zh);
            }


            if (rankVo.getPm25() != null) {
                Double pm25 = Double.valueOf(rankVo.getPm25());
                double pm252 = getint(pm25, 0);
                rankVo.setPm25(String.valueOf((int) pm252));
                double pm25Zh = getint(pm252 / 35, 2);
                rankVo.setPm25Zh(pm25Zh);
            }


            if (rankVo.getNo2() != null) {
                Double no2 = Double.valueOf(rankVo.getNo2());
                double no22 = getint(no2, 0);
                rankVo.setNo2(String.valueOf((int) no22));
                double no2zh = getint(no22 / 40, 2);
                rankVo.setNo2Zh(no2zh);
            }

            if (rankVo.getSo2() != null) {
                Double so2 = Double.valueOf(rankVo.getSo2());
                double so22 = getint(so2, 0);
                rankVo.setSo2(String.valueOf((int) so22));
                double so2zh = getint(so22 / 60, 2);
                rankVo.setSo2Zh(so2zh);
            }


        }

        String type = params.get("dateType").toString();
        if ("1".equals(type)) {
            List<RankVo> nowAlllist = dailyStatementDao.selAllRankVo(params);
            String startDay = params.get("startDate").toString();
            String endDay = params.get("endDate").toString();
            int sd = Integer.parseInt(startDay.substring(0, 4)) - 1;
            int ed = Integer.parseInt(endDay.substring(0, 4)) - 1;
            params.put("startDate", sd + startDay.substring(4));
            params.put("endDate", ed + endDay.substring(4));
            List<RankVo> oldAlllist = dailyStatementDao.selAllRankVo(params);
            PageQuery<RankVo> pageQuery2 = new PageQuery<>(pageQuery.getPageNumber(), pageQuery.getPageSize(), params);
            PageQuery<RankVo> paglist2 = dailyStatementDao.selRankVo(pageQuery2);
            List<RankVo> oldlist = paglist2.getList();
            for (RankVo rankVo : oldlist) {

                if (rankVo.getPm10() != null) {
                    Double pm10 = Double.valueOf(rankVo.getPm10());
                    double pm102 = getint(pm10, 0);
                    rankVo.setPm10(String.valueOf((int) pm102));
                    double pm10Zh = getint(pm102 / 70, 2);
                    rankVo.setPm10Zh(pm10Zh);
                }


                if (rankVo.getPm25() != null) {
                    Double pm25 = Double.valueOf(rankVo.getPm25());
                    double pm252 = getint(pm25, 0);
                    rankVo.setPm25(String.valueOf((int) pm252));
                    double pm25Zh = getint(pm252 / 35, 2);
                    rankVo.setPm25Zh(pm25Zh);
                }


                if (rankVo.getNo2() != null) {
                    Double no2 = Double.valueOf(rankVo.getNo2());
                    double no22 = getint(no2, 0);
                    rankVo.setNo2(String.valueOf((int) no22));
                    double no2zh = getint(no22 / 40, 2);
                    rankVo.setNo2Zh(no2zh);
                }

                if (rankVo.getSo2() != null) {
                    Double so2 = Double.valueOf(rankVo.getSo2());
                    double so22 = getint(so2, 0);
                    rankVo.setSo2(String.valueOf((int) so22));
                    double so2zh = getint(so22 / 60, 2);
                    rankVo.setSo2Zh(so2zh);
                }


            }
            //今年数据
            for (RankVo rankVo : nowlist) {
                String siteName = rankVo.getSiteName();
                String time = rankVo.getTime();
                ArrayList<Double> o3list = new ArrayList<>();
                ArrayList<Double> colist = new ArrayList<>();
                for (RankVo vo : nowAlllist) {
                    String substring = time.substring(0, 7);
                    String substring1 = vo.getTime().substring(0, 7);
                    if (siteName.equals(vo.getSiteName()) && substring.equals(substring1)) {
                        String o3 = vo.getO3();
                        if (o3 != null && o3 != "") {
                            double v = Double.parseDouble(o3);
                            if (v > 0) {
                                o3list.add(Double.parseDouble(o3));
                            }

                        }
                        String co = vo.getCo();
                        if (co != null && co != "") {
                            double v = Double.parseDouble(co);
                            if (v > 0) {
                                colist.add(Double.parseDouble(co));
                            }

                        }
                    }
                }
                if (o3list != null && o3list.size() > 0) {
                    Double percentile = Tutil.percentile(o3list, 0.9);
                    double v = percentile / 160;
                    rankVo.setO3(String.valueOf(getint(percentile, 1)));
                    rankVo.setO3Zh(getint(v, 1));
                }
                if (colist != null && colist.size() > 0) {
                    Double percentile = Tutil.percentile(colist, 0.95);
                    double v = percentile / 4;
                    rankVo.setCo(String.valueOf(getint(percentile, 1)));
                    rankVo.setCoZh(getint(v, 1));
                }
                px(rankVo);
            }
            //去年数据
            if (oldlist != null && oldlist.size() > 0) {
                for (RankVo rankVo : oldlist) {
                    String siteName = rankVo.getSiteName();
                    String time = rankVo.getTime();
                    ArrayList<Double> o3list = new ArrayList<>();
                    ArrayList<Double> colist = new ArrayList<>();
                    for (RankVo vo : oldAlllist) {
                        String substring = time.substring(0, 7);
                        String substring1 = vo.getTime().substring(0, 7);
                        if (siteName.equals(vo.getSiteName()) && substring.equals(substring1)) {
                            String o3 = vo.getO3();
                            if (o3 != null && o3 != "") {
                                double v = Double.parseDouble(o3);
                                if (v > 0) {
                                    o3list.add(Double.parseDouble(o3));
                                }

                            }
                            String co = vo.getCo();
                            if (co != null && co != "") {
                                double v = Double.parseDouble(co);
                                if (v > 0) {
                                    colist.add(Double.parseDouble(co));
                                }

                            }
                        }
                    }
                    if (o3list != null && o3list.size() > 0) {
                        Double percentile = Tutil.percentile(o3list, 0.9);
                        double getint = getint(percentile, 0);

                        double v = getint(getint / 160, 2);
                        rankVo.setO3(String.valueOf(getint));
                        rankVo.setO3Zh(v);
                    }
                    if (colist != null && colist.size() > 0) {
                        Double percentile = Tutil.percentile(colist, 0.95);
                        double getint = getint(percentile, 1);

                        double v = getint(getint / 4, 2);

                        rankVo.setCo(String.valueOf(getint));
                        rankVo.setCoZh(v);
                    }

                    px(rankVo);
                }
                for (RankVo qualityCategory : nowlist) {
                    s:
                    for (RankVo qualityCategory2 : oldlist) {
                        String times = qualityCategory.getTime();
                        int sd2 = Integer.parseInt(times.substring(0, 4)) - 1;
                        String noetime = sd2 + times.substring(4);
                        if (qualityCategory.getSiteName().equals(qualityCategory2.getSiteName()) && noetime.equals(qualityCategory2.getTime())) {
                            qualityCategory.setOdlzhzs(qualityCategory2.getZhzs());
                            double v = qualityCategory.getZhzs() - qualityCategory2.getZhzs();
                            if (qualityCategory2.getZhzs() != null && qualityCategory2.getZhzs() != 0) {
                                double v1 = BigDecimal.valueOf(v / qualityCategory2.getZhzs() * 100).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                                qualityCategory.setNewodlzhzs(v1);
                            }
                            break s;
                        } else {
                            qualityCategory.setOdlzhzs((double) 0);
                            qualityCategory.setNewodlzhzs((double) 0);
                        }
                    }
                }

            } else {
                for (RankVo qualityCategory : nowlist) {
                    qualityCategory.setOdlzhzs((double) 0);
                    qualityCategory.setNewodlzhzs((double) 0);
                }
            }
            getSort(nowlist);
            paglist.setList(nowlist);

            return paglist;
        } else if ("2".equals(type)) {

            List<RankVo> nowAlllist = dailyStatementDao.selAllRankVo(params);
            String startDay = params.get("startDate").toString();
            String endDay = params.get("endDate").toString();
            int sd = Integer.parseInt(startDay.substring(0, 4)) - 1;
            int ed = Integer.parseInt(endDay.substring(0, 4)) - 1;
            params.put("startDate", sd + startDay.substring(4));
            params.put("endDate", ed + endDay.substring(4));
            List<RankVo> oldAlllist = dailyStatementDao.selAllRankVo(params);
            PageQuery<RankVo> pageQuery2 = new PageQuery<>(pageQuery.getPageNumber(), pageQuery.getPageSize(), params);
            PageQuery<RankVo> paglist2 = dailyStatementDao.selRankVo(pageQuery2);
            List<RankVo> oldlist = paglist2.getList();
            for (RankVo rankVo : oldlist) {

                if (rankVo.getPm10() != null) {
                    Double pm10 = Double.valueOf(rankVo.getPm10());
                    double pm102 = getint(pm10, 0);
                    rankVo.setPm10(String.valueOf((int) pm102));
                    double pm10Zh = getint(pm102 / 70, 2);
                    rankVo.setPm10Zh(pm10Zh);
                }


                if (rankVo.getPm25() != null) {
                    Double pm25 = Double.valueOf(rankVo.getPm25());
                    double pm252 = getint(pm25, 0);
                    rankVo.setPm25(String.valueOf((int) pm252));
                    double pm25Zh = getint(pm252 / 35, 2);
                    rankVo.setPm25Zh(pm25Zh);
                }


                if (rankVo.getNo2() != null) {
                    Double no2 = Double.valueOf(rankVo.getNo2());
                    double no22 = getint(no2, 0);
                    rankVo.setNo2(String.valueOf((int) no22));
                    double no2zh = getint(no22 / 40, 2);
                    rankVo.setNo2Zh(no2zh);
                }

                if (rankVo.getSo2() != null) {
                    Double so2 = Double.valueOf(rankVo.getSo2());
                    double so22 = getint(so2, 0);
                    rankVo.setSo2(String.valueOf((int) so22));
                    double so2zh = getint(so22 / 60, 2);
                    rankVo.setSo2Zh(so2zh);
                }


            }
            //今年数据
            for (RankVo rankVo : nowlist) {
                String siteName = rankVo.getSiteName();
                String time = rankVo.getTime();
                ArrayList<Double> o3list = new ArrayList<>();
                ArrayList<Double> colist = new ArrayList<>();
                for (RankVo vo : nowAlllist) {
                    String substring = time.substring(0, 4);
                    String substring1 = vo.getTime().substring(0, 4);
                    if (siteName.equals(vo.getSiteName()) && substring.equals(substring1)) {
                        double substring2 = Double.parseDouble(time.substring(5, 7));
                        double substring3 = Double.parseDouble(vo.getTime().substring(5, 7));
                        double getjidu = getjidu(substring2);
                        double getjidu2 = getjidu(substring3);
                        if (getjidu == getjidu2) {
                            String o3 = vo.getO3();
                            if (o3 != null && o3 != "") {
                                double v = Double.parseDouble(o3);
                                if (v > 0) {
                                    o3list.add(Double.parseDouble(o3));
                                }

                            }
                        }
                        if (getjidu == getjidu2) {
                            String co = vo.getCo();
                            if (co != null && co != "") {
                                double v = Double.parseDouble(co);
                                if (v > 0) {
                                    colist.add(Double.parseDouble(co));
                                }

                            }
                        }

                    }
                }
                if (o3list != null && o3list.size() > 0) {
                    Double percentile = Tutil.percentile(o3list, 0.9);
                    double getint = getint(percentile, 0);

                    double v = getint(getint / 160, 2);
                    rankVo.setO3(String.valueOf(getint));
                    rankVo.setO3Zh(v);
                }
                if (colist != null && colist.size() > 0) {
                    Double percentile = Tutil.percentile(colist, 0.95);
                    double getint = getint(percentile, 1);

                    double v = getint(getint / 4, 2);

                    rankVo.setCo(String.valueOf(getint));
                    rankVo.setCoZh(v);
                }

                px(rankVo);
            }
            //去年数据
            if (oldlist != null && oldlist.size() > 0) {
                for (RankVo rankVo : oldlist) {
                    String siteName = rankVo.getSiteName();
                    String time = rankVo.getTime();
                    ArrayList<Double> o3list = new ArrayList<>();
                    ArrayList<Double> colist = new ArrayList<>();
                    for (RankVo vo : oldAlllist) {
                        String substring = time.substring(0, 4);
                        String substring1 = vo.getTime().substring(0, 4);
                        if (siteName.equals(vo.getSiteName()) && substring.equals(substring1)) {
                            double substring2 = Double.parseDouble(time.substring(5, 7));
                            double substring3 = Double.parseDouble(vo.getTime().substring(5, 7));
                            double getjidu = getjidu(substring2);
                            double getjidu2 = getjidu(substring3);
                            if (getjidu == getjidu2) {
                                String o3 = vo.getO3();
                                if (o3 != null && o3 != "") {
                                    double v = Double.parseDouble(o3);
                                    if (v > 0) {
                                        o3list.add(Double.parseDouble(o3));
                                    }

                                }
                            }
                            if (getjidu == getjidu2) {
                                String co = vo.getCo();
                                if (co != null && co != "") {
                                    double v = Double.parseDouble(co);
                                    if (v > 0) {
                                        colist.add(Double.parseDouble(co));
                                    }

                                }
                            }

                        }
                    }
                    if (o3list != null && o3list.size() > 0) {
                        Double percentile = Tutil.percentile(o3list, 0.9);
                        double getint = getint(percentile, 0);

                        double v = getint(getint / 160, 2);
                        rankVo.setO3(String.valueOf(getint));
                        rankVo.setO3Zh(v);
                    }
                    if (colist != null && colist.size() > 0) {
                        Double percentile = Tutil.percentile(colist, 0.95);
                        double getint = getint(percentile, 1);

                        double v = getint(getint / 4, 2);

                        rankVo.setCo(String.valueOf(getint));
                        rankVo.setCoZh(v);
                    }

                    px(rankVo);
                }
                for (RankVo qualityCategory : nowlist) {
                    s:
                    for (RankVo qualityCategory2 : oldlist) {
                        String times = qualityCategory.getTime();
                        String sd2 = Integer.parseInt(times.substring(0, 4)) - 1 + "";
                        String substring = qualityCategory2.getTime().substring(0, 4);
                        if (qualityCategory.getSiteName().equals(qualityCategory2.getSiteName()) && sd2.equals(substring)) {
                            double substring2 = Double.parseDouble(qualityCategory.getTime().substring(5, 7));
                            double substring3 = Double.parseDouble(qualityCategory2.getTime().substring(5, 7));
                            double getjidu = getjidu(substring2);
                            double getjidu2 = getjidu(substring3);
                            if (getjidu == getjidu2) {
                                qualityCategory.setOdlzhzs(qualityCategory2.getZhzs());
                                double v = qualityCategory.getZhzs() - qualityCategory2.getZhzs();

                                if (qualityCategory2.getZhzs() != null && qualityCategory2.getZhzs() != 0) {
                                    double v1 = BigDecimal.valueOf(v / qualityCategory2.getZhzs() * 100).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                                    qualityCategory.setNewodlzhzs(v1);
                                }
                                break s;
                            }

                        } else {
                            qualityCategory.setOdlzhzs((double) 0);
                            qualityCategory.setNewodlzhzs((double) 0);
                        }
                    }
                }

            } else {
                for (RankVo qualityCategory : nowlist) {
                    qualityCategory.setOdlzhzs((double) 0);
                    qualityCategory.setNewodlzhzs((double) 0);
                }
            }
            getSort(nowlist);
            paglist.setList(nowlist);

            return paglist;
        } else if ("3".equals(type)) {

            List<RankVo> nowAlllist = dailyStatementDao.selAllRankVo(params);
            String startDay = params.get("startDate").toString();
            String endDay = params.get("endDate").toString();
            int sd = Integer.parseInt(startDay.substring(0, 4)) - 1;
            int ed = Integer.parseInt(endDay.substring(0, 4)) - 1;
            params.put("startDate", sd + startDay.substring(4));
            params.put("endDate", ed + endDay.substring(4));
            List<RankVo> oldAlllist = dailyStatementDao.selAllRankVo(params);
            PageQuery<RankVo> pageQuery2 = new PageQuery<>(pageQuery.getPageNumber(), pageQuery.getPageSize(), params);
            PageQuery<RankVo> paglist2 = dailyStatementDao.selRankVo(pageQuery2);
            List<RankVo> oldlist = paglist2.getList();
            for (RankVo rankVo : oldlist) {

                if (rankVo.getPm10() != null) {
                    Double pm10 = Double.valueOf(rankVo.getPm10());
                    double pm102 = getint(pm10, 0);
                    rankVo.setPm10(String.valueOf((int) pm102));
                    double pm10Zh = getint(pm102 / 70, 2);
                    rankVo.setPm10Zh(pm10Zh);
                }


                if (rankVo.getPm25() != null) {
                    Double pm25 = Double.valueOf(rankVo.getPm25());
                    double pm252 = getint(pm25, 0);
                    rankVo.setPm25(String.valueOf((int) pm252));
                    double pm25Zh = getint(pm252 / 35, 2);
                    rankVo.setPm25Zh(pm25Zh);
                }


                if (rankVo.getNo2() != null) {
                    Double no2 = Double.valueOf(rankVo.getNo2());
                    double no22 = getint(no2, 0);
                    rankVo.setNo2(String.valueOf((int) no22));
                    double no2zh = getint(no22 / 40, 2);
                    rankVo.setNo2Zh(no2zh);
                }

                if (rankVo.getSo2() != null) {
                    Double so2 = Double.valueOf(rankVo.getSo2());
                    double so22 = getint(so2, 0);
                    rankVo.setSo2(String.valueOf((int) so22));
                    double so2zh = getint(so22 / 60, 2);
                    rankVo.setSo2Zh(so2zh);
                }


            }
            //今年数据
            for (RankVo rankVo : nowlist) {
                String siteName = rankVo.getSiteName();
                String time = rankVo.getTime();
                ArrayList<Double> o3list = new ArrayList<>();
                ArrayList<Double> colist = new ArrayList<>();
                for (RankVo vo : nowAlllist) {
                    String substring = time.substring(0, 4);
                    String substring1 = vo.getTime().substring(0, 4);
                    if (siteName.equals(vo.getSiteName()) && substring.equals(substring1)) {

                        String o3 = vo.getO3();
                        if (o3 != null && o3 != "") {
                            double v = Double.parseDouble(o3);
                            if (v > 0) {
                                o3list.add(Double.parseDouble(o3));
                            }

                        }

                        String co = vo.getCo();
                        if (co != null && co != "") {
                            double v = Double.parseDouble(co);
                            if (v > 0) {
                                colist.add(Double.parseDouble(co));
                            }

                        }
                    }
                }
                if (o3list != null && o3list.size() > 0) {
                    Double percentile = Tutil.percentile(o3list, 0.9);
                    double getint = getint(percentile, 0);

                    double v = getint(getint / 160, 2);
                    rankVo.setO3(String.valueOf(getint));
                    rankVo.setO3Zh(v);
                }
                if (colist != null && colist.size() > 0) {
                    Double percentile = Tutil.percentile(colist, 0.95);
                    double getint = getint(percentile, 1);

                    double v = getint(getint / 4, 2);

                    rankVo.setCo(String.valueOf(getint));
                    rankVo.setCoZh(v);
                }
                px(rankVo);
            }
            //去年数据
            if (oldlist != null && oldlist.size() > 0) {
                for (RankVo rankVo : oldlist) {
                    String siteName = rankVo.getSiteName();
                    String time = rankVo.getTime();
                    ArrayList<Double> o3list = new ArrayList<>();
                    ArrayList<Double> colist = new ArrayList<>();
                    for (RankVo vo : oldAlllist) {
                        String substring = time.substring(0, 4);
                        String substring1 = vo.getTime().substring(0, 4);
                        if (siteName.equals(vo.getSiteName()) && substring.equals(substring1)) {
                            String o3 = vo.getO3();
                            if (o3 != null && o3 != "") {
                                double v = Double.parseDouble(o3);
                                if (v > 0) {
                                    o3list.add(Double.parseDouble(o3));
                                }

                            }
                            String co = vo.getCo();
                            if (co != null && co != "") {
                                double v = Double.parseDouble(co);
                                if (v > 0) {
                                    colist.add(Double.parseDouble(co));
                                }

                            }
                        }
                    }
                    if (o3list != null && o3list.size() > 0) {
                        Double percentile = Tutil.percentile(o3list, 0.9);
                        double getint = getint(percentile, 0);

                        double v = getint(getint / 160, 2);
                        rankVo.setO3(String.valueOf(getint));
                        rankVo.setO3Zh(v);
                    }
                    if (colist != null && colist.size() > 0) {
                        Double percentile = Tutil.percentile(colist, 0.95);
                        double getint = getint(percentile, 1);

                        double v = getint(getint / 4, 2);

                        rankVo.setCo(String.valueOf(getint));
                        rankVo.setCoZh(v);
                    }

                    px(rankVo);
                }
                for (RankVo qualityCategory : nowlist) {
                    s:
                    for (RankVo qualityCategory2 : oldlist) {
                        String times = qualityCategory.getTime();
                        String sd2 = Integer.parseInt(times.substring(0, 4)) - 1 + "";
                        String substring = qualityCategory2.getTime().substring(0, 4);
                        if (qualityCategory.getSiteName().equals(qualityCategory2.getSiteName()) && substring.equals(sd2)) {
                            qualityCategory.setOdlzhzs(qualityCategory2.getZhzs());
                            double v = qualityCategory.getZhzs() - qualityCategory2.getZhzs();

                            if (qualityCategory2.getZhzs() != null && qualityCategory2.getZhzs() != 0) {
                                double v1 = BigDecimal.valueOf(v / qualityCategory2.getZhzs() * 100).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                                qualityCategory.setNewodlzhzs(v1);
                            }
                            break s;
                        } else {
                            qualityCategory.setOdlzhzs((double) 0);
                            qualityCategory.setNewodlzhzs((double) 0);
                        }
                    }
                }

            } else {
                for (RankVo qualityCategory : nowlist) {
                    qualityCategory.setOdlzhzs((double) 0);
                    qualityCategory.setNewodlzhzs((double) 0);
                }
            }
            getSort(nowlist);
            paglist.setList(nowlist);

            return paglist;
        } else if ("4".equals(type)) {

            List<RankVo> nowAlllist = dailyStatementDao.selAllRankVo(params);
            String startDay = params.get("startDate").toString();
            String endDay = params.get("endDate").toString();
            int sd = Integer.parseInt(startDay.substring(0, 4)) - 1;
            int ed = Integer.parseInt(endDay.substring(0, 4)) - 1;
            params.put("startDate", sd + startDay.substring(4));
            params.put("endDate", ed + endDay.substring(4));
            List<RankVo> oldAlllist = dailyStatementDao.selAllRankVo(params);
            PageQuery<RankVo> pageQuery2 = new PageQuery<>(pageQuery.getPageNumber(), pageQuery.getPageSize(), params);
            PageQuery<RankVo> paglist2 = dailyStatementDao.selRankVo(pageQuery2);
            List<RankVo> oldlist = paglist2.getList();
            for (RankVo rankVo : oldlist) {

                if (rankVo.getPm10() != null) {
                    Double pm10 = Double.valueOf(rankVo.getPm10());
                    double pm102 = getint(pm10, 0);
                    rankVo.setPm10(String.valueOf((int) pm102));
                    double pm10Zh = getint(pm102 / 70, 2);
                    rankVo.setPm10Zh(pm10Zh);
                }


                if (rankVo.getPm25() != null) {
                    Double pm25 = Double.valueOf(rankVo.getPm25());
                    double pm252 = getint(pm25, 0);
                    rankVo.setPm25(String.valueOf((int) pm252));
                    double pm25Zh = getint(pm252 / 35, 2);
                    rankVo.setPm25Zh(pm25Zh);
                }


                if (rankVo.getNo2() != null) {
                    Double no2 = Double.valueOf(rankVo.getNo2());
                    double no22 = getint(no2, 0);
                    rankVo.setNo2(String.valueOf((int) no22));
                    double no2zh = getint(no22 / 40, 2);
                    rankVo.setNo2Zh(no2zh);
                }

                if (rankVo.getSo2() != null) {
                    Double so2 = Double.valueOf(rankVo.getSo2());
                    double so22 = getint(so2, 0);
                    rankVo.setSo2(String.valueOf((int) so22));
                    double so2zh = getint(so22 / 60, 2);
                    rankVo.setSo2Zh(so2zh);
                }


            }
            //今年数据
            for (RankVo rankVo : nowlist) {
                String siteName = rankVo.getSiteName();
                String time = rankVo.getTime();
                ArrayList<Double> o3list = new ArrayList<>();
                ArrayList<Double> colist = new ArrayList<>();
                for (RankVo vo : nowAlllist) {
                    if (siteName.equals(vo.getSiteName())) {
                        String o3 = vo.getO3();
                        if (o3 != null && o3 != "") {
                            double v = Double.parseDouble(o3);
                            if (v > 0) {
                                o3list.add(Double.parseDouble(o3));
                            }

                        }
                        String co = vo.getCo();
                        if (co != null && co != "") {
                            double v = Double.parseDouble(co);
                            if (v > 0) {
                                colist.add(Double.parseDouble(co));
                            }

                        }
                    }
                }
                if (o3list != null && o3list.size() > 0) {
                    Double percentile = Tutil.percentile(o3list, 0.9);
                    double getint = getint(percentile, 0);

                    double v = getint(getint / 160, 2);
                    rankVo.setO3(String.valueOf(getint));
                    rankVo.setO3Zh(v);
                }
                if (colist != null && colist.size() > 0) {
                    Double percentile = Tutil.percentile(colist, 0.95);
                    double getint = getint(percentile, 1);

                    double v = getint(getint / 4, 2);

                    rankVo.setCo(String.valueOf(getint));
                    rankVo.setCoZh(v);
                }

                px(rankVo);
            }
            //去年数据
            if (oldlist != null && oldlist.size() > 0) {
                for (RankVo rankVo : oldlist) {
                    String siteName = rankVo.getSiteName();
                    String time = rankVo.getTime();
                    ArrayList<Double> o3list = new ArrayList<>();
                    ArrayList<Double> colist = new ArrayList<>();
                    for (RankVo vo : oldAlllist) {
                        if (siteName.equals(vo.getSiteName())) {
                            String o3 = vo.getO3();
                            if (o3 != null && o3 != "") {
                                double v = Double.parseDouble(o3);
                                if (v > 0) {
                                    o3list.add(Double.parseDouble(o3));
                                }

                            }
                            String co = vo.getCo();
                            if (co != null && co != "") {
                                double v = Double.parseDouble(co);
                                if (v > 0) {
                                    colist.add(Double.parseDouble(co));
                                }

                            }
                        }
                    }
                    if (o3list != null && o3list.size() > 0) {
                        Double percentile = Tutil.percentile(o3list, 0.9);
                        double getint = getint(percentile, 0);

                        double v = getint(getint / 160, 2);
                        rankVo.setO3(String.valueOf(getint));
                        rankVo.setO3Zh(v);
                    }
                    if (colist != null && colist.size() > 0) {
                        Double percentile = Tutil.percentile(colist, 0.95);
                        double getint = getint(percentile, 1);

                        double v = getint(getint / 4, 2);

                        rankVo.setCo(String.valueOf(getint));
                        rankVo.setCoZh(v);
                    }

                    px(rankVo);
                }
                for (RankVo qualityCategory : nowlist) {
                    s:
                    for (RankVo qualityCategory2 : oldlist) {

                        if (qualityCategory.getSiteName().equals(qualityCategory2.getSiteName())) {
                            qualityCategory.setOdlzhzs(qualityCategory2.getZhzs());
                            double v = qualityCategory.getZhzs() - qualityCategory2.getZhzs();
                            if (qualityCategory2.getZhzs() != null && qualityCategory2.getZhzs() != 0) {
                                double v1 = BigDecimal.valueOf(v / qualityCategory2.getZhzs() * 100).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                                qualityCategory.setNewodlzhzs(v1);
                            }
                            break s;
                        } else {
                            qualityCategory.setOdlzhzs((double) 0);
                            qualityCategory.setNewodlzhzs((double) 0);
                        }
                    }
                }

            } else {
                for (RankVo qualityCategory : nowlist) {
                    qualityCategory.setOdlzhzs((double) 0);
                    qualityCategory.setNewodlzhzs((double) 0);
                }
            }
            getSort(nowlist);
            paglist.setList(nowlist);

            return paglist;
        }
        return null;
    }

    @Override
    public PageQuery<PrimaryDays> primaryList(PageQuery<PrimaryDays> pageQuery) {
        return dailyStatementDao.primaryList(pageQuery);
    }

    @Override
    public JSONObject primaryGraphicList(Map<String, Object> params) {
        JSONObject result = new com.alibaba.fastjson.JSONObject();
        String type = (String) params.get("stationType");
        String timeType = (String) params.get("timeType");
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        Map<String, List<PrimaryDays>> map = new LinkedHashMap<>();
        List<PrimaryDays> list;
        List<String> xItem = null;
        list = dailyStatementDao.primaryGraphicList(params);
        try {
            if ("1".equals(timeType)) {
                xItem = DateUtils.getMonthBetween(startDate, endDate);
            } else if ("2".equals(timeType)) {
                xItem = FormatEntity.formatXItemBySeason(startDate, endDate);
            } else if ("3".equals(timeType)) {
                xItem = FormatEntity.formatXItemByYear(startDate, endDate);
            } else if ("4".equals(timeType)) {
                xItem = new ArrayList<>();
                xItem.add(list.get(0).getDataTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (PrimaryDays primary : list) {
            String dataTime = primary.getDataTime();
            if ("2".equals(timeType)) {
                primary.setDataTime(FormatEntity.formatSingleSeasonByYear(dataTime));
            }
            if (map.containsKey(primary.getSiteName())) {
                map.get(primary.getSiteName()).add(primary);
            } else {
                List<PrimaryDays> p = new ArrayList<>();
                p.add(primary);
                map.put(primary.getSiteName(), p);
            }
        }
        result.put("xItem", xItem);
        result.put("data", map);
        return result;
    }

    @Override
    public PageQuery<Grade> gradeList(PageQuery<Grade> pageQuery) {
        return dailyStatementDao.gradeList(pageQuery);
    }

    @Override
    public List<PrimaryDays> primaryList(Map<String, Object> params) {
        return dailyStatementDao.primaryListToExcel(params);
    }

    @Override
    public List<Grade> gradeList(Map<String, Object> params) {
        return dailyStatementDao.gradeListToExcel(params);
    }


    public static double getjidu(double s) {
        if (0 < s && s <= 3) {
            return 1;
        }
        if (4 <= s && s <= 6) {
            return 2;
        }
        if (7 <= s && s <= 9) {
            return 3;
        }
        if (10 <= s && s <= 12) {
            return 4;
        }
        return 1;
    }

    // 最大单项指数和最大污染物
    private void px(RankVo data) {
        ArrayList<Px> list = new ArrayList<>();
        if (data.getPm25Zh() != null)
            list.add(new Px("PM25", data.getPm25Zh()));
        if (data.getPm10Zh() != null)
            list.add(new Px("PM10", data.getPm10Zh()));
        if (data.getSo2Zh() != null)
            list.add(new Px("SO2", data.getSo2Zh()));
        if (data.getNo2Zh() != null)
            list.add(new Px("NO2", data.getNo2Zh()));
        if (data.getO3Zh() != null)
            list.add(new Px("O3", data.getO3Zh()));
        if (data.getCoZh() != null)
            list.add(new Px("CO", data.getCoZh()));
        Collections.sort(list, new Comparator<Px>() {
            @Override
            public int compare(Px o1, Px o2) {
                //升序
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        if (list.size() > 0) {
            data.setBigOne(list.get(list.size() - 1).getValue());
            data.setBigPre(list.get(list.size() - 1).getName());
            data.setZhzs(new BigDecimal(list.stream().mapToDouble(Px::getValue).sum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            data.setValue(new BigDecimal(list.stream().mapToDouble(Px::getValue).sum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
    }

    public static List<RankVo> getSort(List<RankVo> voList) {
        Collections.sort(voList, new Comparator<RankVo>() {
            @Override
            public int compare(RankVo o1, RankVo o2) {
                //升序
                return o1.getZhzs().compareTo(o2.getZhzs());
            }
        });
        return voList;
    }

    //四舍六入  五 奇进偶舍
    public double getint(double num, int n) {
        double pow = Math.pow(10, n);//10n次方
        double v = num * pow;
        String s = String.valueOf(v);
        String[] split = s.split("\\.");//通过.切割
        if (split.length > 1) {
            String c = String.valueOf(split[1].charAt(0));//判断舍进为的值
            if (c.equals("5")) {//如果是5就要判断
                if (split[1].length() > 1) {//如果大于1所以后面还有小数点
                    Long substring = Long.parseLong(split[1].substring(1));//求判断为5后面的数是不是全为零
                    if (substring > 0) {//如果大于0说明后面数不全为零
                        double round = Math.round(v);
                        double v1 = round / pow;
                        return v1;
                    } else {
                        double round = Math.round(v) - 1;
                        return round / pow;
                    }
                } else {//全为零  看5前面的是奇数还是偶数  奇进偶舍
                    int c1 = split[0].charAt(split[0].length() - 1);
                    if (c1 % 2 == 1) {
                        double round = Math.round(v);
                        return round / pow;

                    } else {
                        double round = Math.round(v) - 1;
                        return round / pow;

                    }
                }
            } else {
                double d = Math.round(v);
                return d / pow;
            }
        } else {
            double d = Math.round(v);
            return d / pow;
        }
    }

    //1,普通年 2 润年
    int getYear(String time) {
        int year = Integer.parseInt(time.substring(0, 4));
        if (year % 4 == 0) {
            return 2;
        } else {
            return 1;
        }
    }

    int getjiduDays(String time) {
        int year = getYear(time);
        if (year == 1) {
            int mouth = Integer.parseInt(time.substring(5, 7));
            if (mouth < 4) {
                return 90;
            }
            if (mouth >= 4 && mouth <= 6) {
                return 91;
            }
            if (mouth >= 7 && mouth <= 9) {
                return 92;
            }
            if (mouth >= 10) {
                return 92;
            }
        } else {
            int mouth = Integer.parseInt(time.substring(5, 7));
            if (mouth < 4) {
                return 91;
            }
            if (mouth >= 4 && mouth <= 6) {
                return 91;
            }
            if (mouth >= 7 && mouth <= 9) {
                return 92;
            }
            if (mouth >= 10) {
                return 92;
            }
        }

        return 92;
    }

    @Override
    public List<AirStationInfoVo> getStaionInfos() {
        List<AirStationInfoVo>  type = dailyStatementDao.getStaionInfos( );
        return type;
    }

}
