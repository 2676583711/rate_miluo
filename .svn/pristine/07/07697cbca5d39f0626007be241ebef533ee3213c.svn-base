package com.rate.web.statistic.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.statistic.dto.LayResult;
import com.rate.web.statistic.service.DailyStatementService;
import com.rate.web.statistic.util.ExportExcelStatementUtil;
import com.rate.web.statistic.vo.ConcentrationVO;
import com.rate.web.statistic.vo.RankVo;
import com.rate.web.utils.ReflectUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 全区空气质量综合指数报表
 *
 * @ClassName ComprehensiveController
 * @Author LiuYong
 * @Date 2019/6/26 11:32
 * @Version 1.0
 **/
@Controller
@RequestMapping("statistic/comprehensive")
public class ComprehensiveController extends BaseController {

    @Autowired
    private DailyStatementService dailyStatementService;

    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping("")
    public String goComprehensivePage() {
		Long roleId = getRoleId();
		if (roleId == 1 || roleId == 73 || roleId == 74 || roleId == 75 || roleId == 77 || roleId == 81) {
			return "/statistic/comprehensiveList";
		} else {
			return "/statistic/404";
		}
    }

    @ResponseBody
    @GetMapping("/list3")
    public PageUtils list3(HttpServletResponse response, @RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
        String dateType = params.get("dateType").toString();

        String siteCodes = (String) params.get("siteCodes");

        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        if (dateType.equals("1")){
            String startMonth = params.get("startMonth").toString();
            String endMonth = params.get("endMonth").toString();
            if (startMonth.equals("")){
                return null;
            }
            String startMonths[] = startMonth.split("-");
            String endMonths[] = endMonth.split("-");
            String statTime = getFirstDayOfMonth1(Integer.parseInt(startMonths[0]),Integer.parseInt(startMonths[1]));
            String endTime  =getLastDayOfMonth1(Integer.parseInt(endMonths[0]),Integer.parseInt(endMonths[1]));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("2")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String jd1 = params.get("jd1").toString();
            String jd2 = params.get("jd2").toString();
            String statTime = startYear.concat(getStartQuarter(jd1));
            String endTime = endYear.concat(getEndQuarter(jd2));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("3")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String statTime = startYear.concat("-01-01");
            String endTime = endYear.concat("-12-31");
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else {
            String startDay = params.get("startDay").toString();
            String endDay = params.get("endDay").toString();
            params.put("startDate",startDay);
            params.put("endDate",endDay);
        }
        PageQuery<RankVo> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<RankVo> page = dailyStatementService.selRankVo(pageQuery,params);
        if (page==null){
            return null;
        }
        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());

        return  pageUtils;

    }

    @ResponseBody
    @RequestMapping("/list4")
    public LayResult list4(HttpServletResponse response, @RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
        String dateType = params.get("dateType").toString();
        String siteCodes = (String) params.get("siteCodes");

        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        if (dateType.equals("1")){
            String startMonth = params.get("startMonth").toString();
            String endMonth = params.get("endMonth").toString();
            if (startMonth.equals("")){
                return null;
            }
            String startMonths[] = startMonth.split("-");
            String endMonths[] = endMonth.split("-");
            String statTime = getFirstDayOfMonth1(Integer.parseInt(startMonths[0]),Integer.parseInt(startMonths[1]));
            String endTime  =getLastDayOfMonth1(Integer.parseInt(endMonths[0]),Integer.parseInt(endMonths[1]));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("2")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String jd1 = params.get("jd1").toString();
            String jd2 = params.get("jd2").toString();
            String statTime = startYear.concat(getStartQuarter(jd1));
            String endTime = endYear.concat(getEndQuarter(jd2));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("3")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String statTime = startYear.concat("-01-01");
            String endTime = endYear.concat("-12-31");
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else {
            String startDay = params.get("startDay").toString();
            String endDay = params.get("endDay").toString();
            params.put("startDate",startDay);
            params.put("endDate",endDay);
        }

        PageQuery<RankVo> pageQuery = new PageQuery<>(1, 1000, params);
        PageQuery<RankVo> page = dailyStatementService.selRankVo(pageQuery,params);

        if (page==null){
            return LayResult.success(new ArrayList<RankVo>(), new ArrayList<>());
        }
        List<RankVo> list= page.getList();
        if (list == null || list.isEmpty()) {
            return LayResult.success(list, new ArrayList<>());
        }
        Map<String,List<RankVo>> map= new HashMap<>();
        for (RankVo s:list){
            if (map.containsKey(s.getSiteName())){
                List<RankVo> list1 = map.get(s.getSiteName());
                list1.add(s);
            }else {
                List<RankVo> l=new ArrayList<>();
                l.add(s);
                map.put(s.getSiteName(),l);
            }
        }
        List<ConcentrationVO> resultList = new ArrayList<>();
        if(dateType.equals("1")) {
            List<String> hours = new ArrayList<>();
            for (String in : map.keySet()) {
                hours.add(in);
                //map.keySet()返回的是所有key的值
                List<RankVo> list1 = map.get(in);//得到每个key多对用value的值
                ConcentrationVO vo = new ConcentrationVO();
                vo.setSiteName(list1.get(0).getSiteName());
                vo.setAreaName(list1.get(0).getAreaName());
                Map<String, Object> dateMap = new HashMap<>();

                for (RankVo s : list1) {
                    dateMap.put(s.getTime().substring(0, 4)+"_"+s.getTime().substring(5, 7), s.getZhzs());
                }
                ConcentrationVO target = (ConcentrationVO) ReflectUtil.getTarget(vo, dateMap);

                resultList.add(target);

            }
        }else if(dateType.equals("2")){
            List<String> hours = new ArrayList<>();
            for (String in : map.keySet()) {
                hours.add(in);
                //map.keySet()返回的是所有key的值
                List<RankVo> list1 = map.get(in);//得到每个key多对用value的值
                ConcentrationVO vo = new ConcentrationVO();
                vo.setSiteName(list1.get(0).getSiteName());
                Map<String, Object> dateMap = new HashMap<>();

                for (RankVo s : list1) {
                    String substring = s.getTime().substring(5, 7);

                    String jidu= getjidu(Integer.parseInt(substring));
                    dateMap.put(s.getTime().substring(0, 4)+jidu, s.getZhzs());
                }
                ConcentrationVO target = (ConcentrationVO) ReflectUtil.getTarget(vo, dateMap);

                resultList.add(target);}
        }else if(dateType.equals("3")){
            List<String> hours = new ArrayList<>();
            for (String in : map.keySet()) {
                hours.add(in);
                //map.keySet()返回的是所有key的值
                List<RankVo> list1 = map.get(in);//得到每个key多对用value的值
                ConcentrationVO vo = new ConcentrationVO();
                vo.setSiteName(list1.get(0).getSiteName());
                vo.setAreaName(list1.get(0).getAreaName());
                Map<String, Object> dateMap = new HashMap<>();

                for (RankVo s : list1) {
                    dateMap.put(s.getTime().substring(0, 4), s.getZhzs());
                }
                ConcentrationVO target = (ConcentrationVO) ReflectUtil.getTarget(vo, dateMap);

                resultList.add(target);}
        }else {

            String startDay = params.get("startDay").toString();
            for (RankVo qualityCategory : list) {
                ConcentrationVO vo = new ConcentrationVO();
                vo.setSiteName(qualityCategory.getSiteName());
                vo.setAreaName(qualityCategory.getAreaName());
                Map<String, Object> dateMap = new HashMap<>();
                dateMap.put(startDay.substring(0,4),qualityCategory.getZhzs());
                ConcentrationVO target = (ConcentrationVO) ReflectUtil.getTarget(vo, dateMap);
                resultList.add(target);
            }
        }
        return LayResult.success(resultList, new ArrayList<>());
    }

    @RequestMapping("/exportExcel1")
    @ResponseBody
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response,
                            HttpServletRequest request) throws Exception {
        List<RankVo> categoryList;
        String dateType = params.get("dateType").toString();

        String siteCodes = (String) params.get("siteCodes");

        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        if (dateType.equals("1")){
            String startMonth = params.get("startMonth").toString();
            String endMonth = params.get("endMonth").toString();
            if (startMonth.equals("")){
                categoryList= null;
            }
            String startMonths[] = startMonth.split("-");
            String endMonths[] = endMonth.split("-");
            String statTime = getFirstDayOfMonth1(Integer.parseInt(startMonths[0]),Integer.parseInt(startMonths[1]));
            String endTime  =getLastDayOfMonth1(Integer.parseInt(endMonths[0]),Integer.parseInt(endMonths[1]));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("2")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String jd1 = params.get("jd1").toString();
            String jd2 = params.get("jd2").toString();
            String statTime = startYear.concat(getStartQuarter(jd1));
            String endTime = endYear.concat(getEndQuarter(jd2));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("3")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String statTime = startYear.concat("-01-01");
            String endTime = endYear.concat("-12-31");
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else {
            String startDay = params.get("startDay").toString();
            String endDay = params.get("endDay").toString();
            params.put("startDate",startDay);
            params.put("endDate",endDay);
        }
        PageQuery<RankVo> pageQuery = new PageQuery<>(1, 1000, params);
        PageQuery<RankVo> page = dailyStatementService.selRankVo(pageQuery,params);
        if (page==null){
            categoryList= null;
        }else {
            categoryList=page.getList();
        }
        // 排名
        for (int i = 0; i < categoryList.size(); i++) {
            if (i > 0) {
                if (categoryList.get(i).getValue().equals(categoryList.get(i - 1).getValue())) {
                    categoryList.get(i).setRanking(categoryList.get(i - 1).getRanking());
                } else {
                    categoryList.get(i).setRanking(i + 1);
                }
            } else {
                categoryList.get(i).setRanking(i + 1);
            }

        }
        String time1 = (String) params.get("startDate");
        String  time2= (String) params.get("endDate");
        this.doExportXls1(response, request, categoryList,dateType,time1,time2, RankVo.class, "综合指数(排名)");

    }

    protected <T> void doExportXls1(HttpServletResponse response, HttpServletRequest request,
                                    List<RankVo> dataSet, String s, String time1, String time2, Class<?> pojoClass, String title) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream fOut = null;

        try {
            response.reset();
            response.setHeader("Cache-Control", "private");
            response.setHeader("Pragma", "private");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Type", "application/force-download");
            String workbookname = URLEncoder.encode(title + ".xls", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + workbookname);
            ExportExcelStatementUtil excelStatementUtil = new ExportExcelStatementUtil();
            HSSFWorkbook wb = excelStatementUtil.exportExcllentCategory5(dataSet,s,time1,time2);
            fOut = response.getOutputStream();
            wb.write(fOut);
        } catch (Exception var15) {
            var15.printStackTrace();
            throw new Exception("导出失败！");
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException var14) {
                var14.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/exportExcel2")
    @ResponseBody
    public void exportExcel2( @RequestParam Map<String, Object> params, HttpServletResponse response,
                              HttpServletRequest request)throws Exception {
        List<String> listTime = new ArrayList<>();
        String startDay2;
        String endDay2;
        String dateType = params.get("dateType").toString();
        if (dateType.equals("1")) {
            String startMonth = params.get("startMonth").toString();
            String endMonth = params.get("endMonth").toString();
            if (startMonth.equals("")) {
                listTime= null;
            }else {
                listTime = getRangeSet(startMonth,endMonth);
            }
        } else if (dateType.equals("2")) {
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String jd1 = params.get("jd1").toString();
            String jd2 = params.get("jd2").toString();
            String statTime = startYear.concat(getStartQuarter(jd1));
            String endTime = endYear.concat(getEndQuarter(jd2));
            listTime= getRangeSet_Q(statTime,endTime);

        } else if (dateType.equals("3")) {
            String startYear1 =  params.get("startYear").toString();
            String endYear1 = params.get("endYear").toString();
            int startYear = Integer.parseInt(startYear1);
            int endYear = Integer.parseInt(endYear1);
            while (startYear <=endYear){
                listTime.add(startYear+"");
                startYear++;
            }
        } else {
            startDay2 = params.get("startDay").toString();
            endDay2 = params.get("endDay").toString();
            listTime.add(startDay2+"至"+endDay2);
        }


        String siteCodes = (String) params.get("siteCodes");
        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        if (dateType.equals("1")){
            String startMonth = params.get("startMonth").toString();
            String endMonth = params.get("endMonth").toString();

            String startMonths[] = startMonth.split("-");
            String endMonths[] = endMonth.split("-");
            String statTime = getFirstDayOfMonth1(Integer.parseInt(startMonths[0]),Integer.parseInt(startMonths[1]));
            String endTime  =getLastDayOfMonth1(Integer.parseInt(endMonths[0]),Integer.parseInt(endMonths[1]));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("2")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String jd1 = params.get("jd1").toString();
            String jd2 = params.get("jd2").toString();
            String statTime = startYear.concat(getStartQuarter(jd1));
            String endTime = endYear.concat(getEndQuarter(jd2));
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else if (dateType.equals("3")){
            String startYear = params.get("startYear").toString();
            String endYear = params.get("endYear").toString();
            String statTime = startYear.concat("-01-01");
            String endTime = endYear.concat("-12-31");
            params.put("startDate",statTime);
            params.put("endDate",endTime);
        }else {
            String startDay = params.get("startDay").toString();
            String endDay = params.get("endDay").toString();
            params.put("startDate",startDay);
            params.put("endDate",endDay);
        }
        List<RankVo> list;
        PageQuery<RankVo> pageQuery = new PageQuery<>(1, 1000, params);
        PageQuery<RankVo> page = dailyStatementService.selRankVo(pageQuery,params);
        list = page.getList();

        Map<String, Object> result = new HashMap<>(4);
        List<ConcentrationVO> result2 = new ArrayList<>();
        if (list != null){
            for (RankVo element : list) {
                String times = element.getTime();
                if ("1".equals(dateType)){
                    element.setTime(times.substring(0,7));
                }else if ("2".equals(dateType)){
                    String nian = times.substring(0, 4);
                    int yue = Integer.parseInt(times.substring(5, 7));
                    String getjidu = getjidu(yue);
                    element.setTime(nian+"年第"+getjidu+"季度");
                }else if ("3".equals(dateType)){
                    element.setTime(times.substring(0,4));
                }else if ("4".equals(dateType)){
                    startDay2 = params.get("startDay").toString();
                    endDay2 = params.get("endDay").toString();
                    element.setTime(startDay2+"至"+endDay2);
                }
            }
            Set<String> codes = list.stream().map(RankVo::getSiteName).collect(Collectors.toSet());
            Iterator<String> iterator = codes.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                List resourceList = new ArrayList();
                ConcentrationVO vo = new ConcentrationVO();
                for (RankVo element : list) {
                    if (next.equals(element.getSiteName())) {
                        resourceList.add(element);
                        vo.setAreaName(element.getAreaName());
                    }
                }

                vo.setSiteName(next);
                vo.setLists(resourceList);
                result2.add(vo);
            }
        }
        result.put("time", listTime);
        result.put("data", result2);
        this.doExportXls2(response, request, result, ConcentrationVO.class, "空气综合变化趋势");

    }
    protected <T> void doExportXls2(HttpServletResponse response, HttpServletRequest request,
                                    Map<String, Object> dataSet, Class<?> pojoClass, String title) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream fOut = null;

        try {
            response.reset();
            response.setHeader("Cache-Control", "private");
            response.setHeader("Pragma", "private");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Type", "application/force-download");
            String workbookname = URLEncoder.encode(title + ".xls", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + workbookname);
            ExportExcelStatementUtil excelStatementUtil = new ExportExcelStatementUtil();
            HSSFWorkbook wb = excelStatementUtil.exportExcllentCategory8(dataSet);

            fOut = response.getOutputStream();
            wb.write(fOut);
        } catch (Exception var15) {
            var15.printStackTrace();
            throw new Exception("导出失败！");
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException var14) {
                var14.printStackTrace();
            }
        }
    }

    /**
     *根据时间范围获得季度集
     * @return
     */
    public  List<String> getRangeSet_Q(String beginDate,String endDate){
        /*      Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
          Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
          如果业务数据存在相等的时候，而且相等时也需要做相应的业务判断或处理时，你需要使用：！Date1.after(Date2);*/
        List<String> rangeSet =null;
        SimpleDateFormat sdf = null;
        Date begin_date = null;
        Date end_date = null;
        String[] numStr =null;
        String Q=null;
        rangeSet = new java.util.ArrayList<String>();
        sdf = new SimpleDateFormat("yyyy-MM");
        try {
            begin_date = sdf.parse(beginDate);//定义起始日期
            end_date = sdf.parse(endDate);//定义结束日期
        } catch (ParseException e) {
            System.out.println("时间转化异常，请检查你的时间格式是否为yyyy-MM或yyyy-MM-dd");
        }
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(begin_date);//设置日期起始时间
        while(!dd.getTime().after(end_date)){//判断是否到结束日期
            numStr=  sdf.format(dd.getTime()).split("-",0);
            Q = getQuarter(Integer.valueOf(numStr[1]))+"";
            System.out.println(numStr[0].toString()+"年"+numStr[1].toString()+"月"+"为"+numStr[0].toString()+"年第"+Q+"季度");
            if (!rangeSet.contains(numStr[0].toString()+"年第"+Q+"季度")){
                rangeSet.add(numStr[0].toString()+"年第"+Q+"季度");
            }

            dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
        }
        return rangeSet;
    }

    public  List<String> getRangeSet(String beginDate,String endDate){
        /*      Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
          Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
          如果业务数据存在相等的时候，而且相等时也需要做相应的业务判断或处理时，你需要使用：！Date1.after(Date2);*/
        List<String> rangeSet =null;
        SimpleDateFormat sdf = null;
        Date begin_date = null;
        Date end_date = null;
        rangeSet = new java.util.ArrayList<String>();
        sdf = new SimpleDateFormat("yyyy-MM");
        try {
            begin_date = sdf.parse(beginDate);//定义起始日期
            end_date = sdf.parse(endDate);//定义结束日期
        } catch (ParseException e) {
            System.out.println("时间转化异常，请检查你的时间格式是否为yyyy-MM或yyyy-MM-dd");
        }
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(begin_date);//设置日期起始时间
        while(!dd.getTime().after(end_date)){//判断是否到结束日期
            rangeSet.add(sdf.format(dd.getTime()));
            dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
        }
        return rangeSet;
    }

    /**
     * 根据月获得季度
     * @param month  月
     * @return  季度
     */
    private  int getQuarter(int month) {
        if(month == 1 || month == 2 || month == 3){
            return 1;
        }else if(month == 4 || month == 5 || month == 6){
            return  2;
        }else if(month == 7 || month == 8 || month == 9){
            return 3;
        }else{
            return 4;
        }
    }

    public String getStartQuarter(String quarter){
        if (quarter.equals("1")){
            return "-01-01";
        }else if (quarter.equals("2")){
            return "-04-01";
        }else if (quarter.equals("3")){
            return "-07-01";
        }else {
            return "-10-01";
        }
    }

    public String getEndQuarter(String quarter){
        if (quarter.equals("1")){
            return "-03-31";
        }else if (quarter.equals("2")){
            return "-06-30";
        }else if (quarter.equals("3")){
            return "-09-30";
        }else {
            return "-12-31";
        }
    }


    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static String getFirstDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public String getjidu(int a){
        String s="";
        if (a<4){
            s="1";
        }
        if(a<=6 && a>3){
            s="2";
        }
        if(7<a && a<=9){
            s="3";
        }
        if(9<a && a <=12){
            s="4";
        }
        return s;
    }

}
