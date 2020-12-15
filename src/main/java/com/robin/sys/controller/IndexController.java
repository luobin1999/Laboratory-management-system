package com.robin.sys.controller;

import com.robin.sys.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute(user);
        return "index";
    }
}
