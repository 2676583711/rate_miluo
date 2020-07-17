package com.rate.web.alarm.inter;

import com.mascloud.sdkclient.Client;
import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.DictService;
import com.rate.system.rate_system.utils.IdGen;
import com.rate.web.alarm.dao.MsgRecordDao;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.MsgRecord;
import com.rate.web.alarm.entity.UserInfo;
import com.rate.web.alarm.service.AlarmService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author chenshixue
 * @Date 2020/1/2
 */
@RequestMapping("/miluoServer")
@RestController
public class Controller {

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private DictService dictService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MsgRecordDao msgRecordDao;

    @RequestMapping(value = "/alarm/message", method = RequestMethod.POST)
    public String sendMessage(@RequestBody List<Long> ids) {
        try {
            List<AlarmEntity> list = alarmService.findByIds(ids);
            String Uid = dictService.listByType("sms_username").get(0).getValue();
            String Key = dictService.listByType("sms_key").get(0).getValue();
            // 登录账户
            Client client = Client.getInstance();
            // 登录地址需另外提供
            boolean isLoggedIn = client.login( "http://112.35.4.197:15000",Uid, Key, "汨罗市环境保护保护局" );
            if (isLoggedIn) {
                UserInfo info = alarmService.findUserByEqumentId(list.get(0).getEquipmentId());
                for (AlarmEntity entity : list) {
                    // 发送的信息内容
                    String smsText = "报警信息：" + info.getSiteName()
                            + "站点" + entity.getName()
                            + "[" + entity.getPollutant() + "]污染物浓度"
                            + entity.getText() + "(" + entity.getDegreeText() + ")";

                    String[] mobiles = null;
                    // 负责人电话
                    MsgRecord record1 = null;
                    MsgRecord record2 = null;
                    Date date = new Date();
                    if (info.getMobile() != null) {
                        mobiles = new String[]{info.getMobile()};
                        record1 = new MsgRecord();
                        record1.setId(IdGen.uuid());
                        record1.setUserId(info.getFzr());
                        record1.setMobile(info.getMobile());
                        record1.setContent(smsText);
                        record1.setCreateTime(date);
                        record1.setMsgType("2");
                        record1.setVideoId(entity.getEquipmentId());
                    }
                    // 环保局电话
                    if (entity.getPushMessage() == 2) {
                        User user = userDao.getUserName("lizhan");
                        if (user.getMobile() != null) {
                            mobiles = (String[]) ArrayUtils.add(mobiles, user.getMobile());  // 推送给负责人和环保局
                            record2 = new MsgRecord();
                            record2.setId(IdGen.uuid());
                            record2.setUserId(user.getUserId());
                            record2.setMobile(user.getMobile());
                            record2.setContent(smsText);
                            record2.setCreateTime(date);
                            record2.setMsgType("2");
                            record2.setVideoId(entity.getEquipmentId());
                        }
                    }
                    // 登录成功，普通短信
                    int result = client.sendDSMS(mobiles, smsText, "", 1,
                            "yKIvhGE1V", null, true );
                    if (result == Client.SUCCESS) {
                        if (record1 != null) {
                            msgRecordDao.insert(record1);
                        }
                        if (record2 != null) {
                            msgRecordDao.insert(record2);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
