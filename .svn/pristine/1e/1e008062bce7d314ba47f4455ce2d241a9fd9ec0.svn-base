package com.rate.web.utils;

import com.alibaba.fastjson.JSON;
import com.rate.system.rate_system.dao.UserDao;

import com.rate.system.rate_system.service.DictService;
import com.rate.system.rate_system.utils.R;
import com.rate.web.alarm.monitor.dao.ExceedDao;
import com.rate.web.alarm.monitor.entity.Exceed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mascloud.sdkclient.Client;

/**
 *  
 *  @ClassName: minuteData
 *  @Description
 *  @Author  xiaoshi
 *  @Date  2019/5/6 15:21
 *  @version 1.00 
 */

@Component
public class LsListData {

    @Autowired
    private ExceedDao exceedDao;


    @Autowired
    private UserDao userDao;

    @Autowired
    private DictService dictService;

    /**
     * 定时任务 获取小型站的数据
     * ,6,11,16,21,26,31,36,41,46,51,56
     */
   @Scheduled(cron = "0,15 * * * * *")
    public void LsSite() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = new GregorianCalendar();
        Date date = new Date();
        String endDate = df.format(date);
        c.setTime(date);//设置参数时间
        c.add(Calendar.SECOND, -15);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
        date = c.getTime(); //这个时间就是日期往后推一天的结果
        String startDate = df.format(date);
        List<Exceed> exceeds = exceedDao.latestTiming(startDate, endDate);
        List<String> equipments = new ArrayList<>();
         if(exceeds.size()==0){
             return;
         }else {
             SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

             for (Exceed exceed : exceeds) {
                     equipments.add(exceed.getSiteCode());
             }
             List<Exceed> exceeds1 = exceedDao.siteByEquipment(equipments);
             Exceed exceed2 = new Exceed();
             List<Exceed> exceed3 = new ArrayList<>();
             for (Exceed exceed : exceeds) {
                 for (Exceed exceed1 : exceeds1) {
                     if (exceed.getSiteCode().equals(exceed1.getEqumentId())) {
                         exceed2.setName(exceed1.getName());
                         if (exceed1.getFzr().matches("[0-9]+")) {
                             exceed2.setFzr(userDao.userById(Integer.parseInt(exceed1.getFzr())).getName());
                             exceed2.setPhone(userDao.userById(Integer.parseInt(exceed1.getFzr())).getMobile());
                             exceed2.setUserId(exceed1.getFzr());
                         } else {
                             exceed2.setFzr(exceed1.getFzr());
                         }
                         exceed2.setExceedsBidDate(exceed.getExceedsBidDate());
                         exceed2.setPollutant(exceed.getPollutant());
                         exceed2.setValue(exceed.getValue());
                         exceed3.add(exceed2);
                     }
                 }
             }
             String Uid = dictService.listByType("sms_username").get(0).getValue();
             String Key = dictService.listByType("sms_key").get(0).getValue();
             String sms= dictService.listByType("sms_switch").get(0).getValue();
             String app= dictService.listByType("App_switch").get(0).getValue();
             String smsText="";
             String phone="";
             Collection<String> ids=new ArrayList<>();
             for (Exceed exceed : exceed3) {

                 smsText = "负责人：" + exceed.getFzr()+"站点名称："+ exceed.getName()  + " 报警级别：最高级别" + "报警类型：因子超标" + "报警内容：" + exceed.getExceedsBidDate()+ exceed.getPollutant() + "污染源浓度超标";
                 phone=exceed.getPhone();
                 ids.add(exceed.getUserId());


                 //推送超标信息
                 if(app.equals("0")){
                     HashMap<String,String> message = new HashMap<>();
                     message.put("police", JSON.toJSONString(exceed));
                     message.put("message",smsText);
                     PushMsgToAll.jgSend(message,ids);
                 }
             }

             if(sms.equals("0")){
                 Client client = Client.getInstance( );
                 // 登录地址需另外提供
                 boolean isLoggedin = client.login( "http://112.35.4.197:15000",
                         Uid, Key, "汨罗市环境保护保护局" );

                 // 普通短信
                 int result = client.sendDSMS(new String[]{phone},
                         smsText, "", 1, "yKIvhGE1V",
                         "null", true );
                 R res = new R();

                 if (result > 0) {
                     System.out.println("UTF8成功发送条数==" + result);
                     res = R.ok("操作成功！");
                 } else {
                     System.out.println();
                     res = R.ok("任务添加成功, 短信发送失败" );
                 }
             }



         }
    }
}
