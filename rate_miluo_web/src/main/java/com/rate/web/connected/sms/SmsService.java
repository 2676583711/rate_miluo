package com.rate.web.connected.sms;

import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.config.Constant;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.entity.Dict;
import com.rate.system.rate_system.service.DictService;
import com.rate.system.rate_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

import static com.rate.system.rate_system.utils.ShiroUtils.getUserId;

/**
 * @ProjectName: rate_miluo_parent
 * @Package: com.rate.web.connected.sms
 * @ClassName: SmsServer
 * @author: xiaoshi
 * @Description: ${description}
 * @Date: 2019/6/17 16:32
 * @Version: 1.0
 */
@Controller
@RequestMapping("/smsIndex")
public class SmsService  extends BaseController {

    private String prefix="module/connected/sms";

    @Autowired
    private DictService dictService;


    @GetMapping("/add")
    String add(Model model) {
        Long userId=getUserId();
        model.addAttribute("userId", userId);
        return prefix + "/add";
    }

    /**
     * @Param [model]
     * @Return java.lang.String
     * @author xiaoshi
     * @Description
     * @Date 2019/7/8
     * @Time 15:21
     **/
    @GetMapping("/switch")
    String getSwitch(Model model) {

        List<Dict> smsDict = dictService.listByType("sms_switch");
        List<Dict> appDict = dictService.listByType("App_switch");
        Dict smsD = smsDict.get(0);
        Dict appD = appDict.get(0);
        String smsDValue = smsD.getValue();
        String appDValue = appD.getValue();
         boolean smsBoolean;
         boolean appBoolean;
        if(smsDValue.equals("0")){
            smsBoolean =true;
        }else{
            smsBoolean =false;
        }
        if(appDValue.equals("0")){
            appBoolean=true;
        }else{
            appBoolean=false;
        }
        Long userId=getUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("smsBoolean",smsBoolean);
        model.addAttribute("appBoolean",appBoolean);
        return prefix + "/show";
    }
    /**
     * 保存字典
     * */
    @Log("保存字典")
    @PostMapping("/saveSwitch")
    @ResponseBody
    R getSaveSwitch(String sms,String app) {

        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String offOnSms="";
         if(sms==null){
             offOnSms="1" ;
         }else if(sms.equals("on")){
             offOnSms="0";
         }
         String offOnApp="";
         if(app==null){
             offOnApp="1";
         }else if(app.equals("on")){
             offOnApp="0";
         }
        List<Dict> smsDict = dictService.listByType("sms_switch");
        List<Dict> appDict = dictService.listByType("App_switch");
        Dict smsD = smsDict.get(0);
        Dict appD = appDict.get(0);
        smsD.setUpdateDate(new Date());
        appD.setUpdateDate(new Date());
        smsD.setValue(offOnSms);
        appD.setValue(offOnApp);
        int n = dictService.update(smsD);
        int v = dictService.update(appD);
        if(n>0 && v>0){
            return R.ok();
        }
        return R.error();
    }
    /**
     * 保存字典
     * */
    @Log("保存字典")
    @PostMapping("/save")
    @ResponseBody
    R save(String name, String value) {

        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        List<Dict> nameDict = dictService.listByType("sms_username");
        List<Dict> valueDict = dictService.listByType("sms_key");
        Dict nameD = nameDict.get(0);
        Dict valueD = valueDict.get(0);
        nameD.setUpdateDate(new Date());
        valueD.setUpdateDate(new Date());
        nameD.setValue(name);
        valueD.setValue(value);
        int n = dictService.update(nameD);
        int v = dictService.update(valueD);
        if(n>0 && v>0){
            return R.ok();
        }
        return R.error();
    }
}
