package com.robin.sys.controller;

import com.robin.sys.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/list/test")
    public String listTest(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "test_list";
    }

    @RequestMapping("/add/test")
    public String testAdd(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "test_add";
    }
}
