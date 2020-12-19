package com.robin.sys.controller;

import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {

    @RequestMapping("/list/file")
    public String listFile(Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        model.addAttribute("user", user);
        return "file_list";
    }
}
