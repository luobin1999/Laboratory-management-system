package com.robin.sys.controller;

import com.robin.sys.access.LoginLimit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    @LoginLimit
    public String index(){
        return "index";
    }
}
