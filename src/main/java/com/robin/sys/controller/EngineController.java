package com.robin.sys.controller;

import com.robin.sys.constant.Constant;
import com.robin.sys.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EngineController {
    @Autowired
    private EngineService engineService;

    @RequestMapping("/engine/identity/zdh")
    @ResponseBody
    public String zdhEngineIdentify(){

        return engineService.engineIdentify(Constant.ENGINE_IDENTIFY_ZDH);
    }

    @RequestMapping("/engine/identity/wdz")
    @ResponseBody
    public String wdzEngineIdentify(){
        return engineService.engineIdentify(Constant.ENGINE_IDENTIFY_WDZ);
    }

    @RequestMapping("/engine/identity/dq")
    @ResponseBody
    public String dqEngineIdentify(){
        return engineService.engineIdentify(Constant.ENGINE_IDENTIFY_DQ);
    }

    @RequestMapping("/engine/identity/ck")
    @ResponseBody
    public String ckEngineIdentify(){
        return engineService.engineIdentify(Constant.ENGINE_IDENTIFY_CK);
    }
}
