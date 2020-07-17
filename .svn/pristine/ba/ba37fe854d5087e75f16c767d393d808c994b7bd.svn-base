package com.rate.web.vidicon.timedTask;

import com.rate.system.rate_system.utils.JSONUtils;
import com.rate.web.vidicon.entity.VidiconOwner;
import com.rate.web.vidicon.service.VidiconOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 萤石云accessKey的自动更新
 *
 * @auther: HuYan
 * @date: 2019/4/9 17:34
 */
@Component
public class AutoUpdateAccessToken {
@Autowired
VidiconOwnerService vidiconOwnerService;
    @Scheduled(cron = "0 0 0 */5 * ?")
    public void update() {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL("https://open.ys7.com/api/lapp/token/get");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("POST", "/api/lapp/token/get HTTP/1.1");
            conn.setRequestProperty("Host", "open.ys7.com");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取appkey和secret
            List<VidiconOwner> vidiconOwners = vidiconOwnerService.getAllVidiconOwner();
            for (VidiconOwner vidiconOwner : vidiconOwners) {
            	 String appKey = vidiconOwner.getAppKey();
            	 String secret =vidiconOwner.getSecret();
                 if (appKey == null || secret == null) {
                     return;
                 }
                 String requestParam = "appKey=" + appKey + "&appSecret=" + secret;
                 // 获取URLConnection对象对应的输出流
                 out = new PrintWriter(conn.getOutputStream());
                 // 发送请求参数
                 out.print(requestParam);
                 // flush输出流的缓冲
                 out.flush();
                 // 定义BufferedReader输入流来读取URL的响应
                 in = new BufferedReader(
                         new InputStreamReader(conn.getInputStream()));
                 String line;
                 while ((line = in.readLine()) != null) {
                     result += line;
                 }
                 Map<String, Object> responseMap = JSONUtils.jsonToMap(result);
                 if (!"200".equals(responseMap.get("code"))) {
                     TimeUnit.MINUTES.sleep(60);
                     update();
                 }
                 //将数据保存至数据库
                 Map<String, Object> data = (Map<String, Object>) responseMap.get("data");
                 vidiconOwner.setAccessToken(data.get("accessToken").toString());
                 vidiconOwner.setTokenUpdateDate(new Date());
                 int rows = vidiconOwnerService.update(vidiconOwner);//更新
                 if (rows == 0) {
                     TimeUnit.MINUTES.sleep(60);
                     update();
                 }
			}

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
            try {
                TimeUnit.MINUTES.sleep(60);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            update();//隔一段时间后再次请求
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
