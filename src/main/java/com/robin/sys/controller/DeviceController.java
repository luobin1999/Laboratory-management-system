package com.robin.sys.controller;

import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeviceController {

    @RequestMapping("/list/device")
    public String listDevice(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "device_list";
    }

    @RequestMapping("/add/device")
    public String deviceAdd(Model model, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() !=3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        model.addAttribute("user", user);
        return "device_add";
    }

    @RequestMapping("/list/device/usage_recode")
    public String listDeviceUsageRecode(Model model, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        model.addAttribute("user", user);
        return "device_usage_record";
    }
}
