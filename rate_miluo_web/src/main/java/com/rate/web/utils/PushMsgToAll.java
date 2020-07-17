package com.rate.web.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Param 
 * @Return 
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/20
 * @Time 14:15
 **/
public class PushMsgToAll {

    private static String apiKey = "e0d2b0cc77ddae950b6ae970";
    private static String secretKey = "5d1a711ea451bfdbda2794c1";

    public static void send(String message) throws PushClientException,PushServerException  {


        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            // 4. specify request arguments
            PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMessage(message);
            // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例70秒后推送

            // 5. http request
            PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }


    public static void jgSend(Map<String,String> message, Collection<String> ids){
        JPushClient jpushClient = new JPushClient(secretKey, apiKey);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.alias(ids))//你项目中的所有用户
//                .setAudience(Audience.registrationId())
                .setNotification(Notification.android(message.get("message"), "超标提醒",message))
                //发送内容,这里不要盲目复制粘贴,这里是我从controller层中拿过来的参数)
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                //这里是指定开发环境,不用设置也没关系
                .setMessage(Message.content(message.get("message")))//自定义信息
                .build();

        try {
            PushResult pu = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
}
