package com.rate.web.audio;


import com.rate.system.rate_system.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/audio")
public class AudioController extends BaseController {


    private String prefix = "/module/audio/video/";


    /**
     * @Param
     * @Return
     * @author xiaoshi
     * @Description
     * @Date 2019/6/17
     * @Time 16:31
     **/
    @GetMapping("/index")
    public String getAirHourSmallStatement(Model model) {
        Long userId=getUserId();
        model.addAttribute("userId", userId);
        return prefix + "show";
    }

}

