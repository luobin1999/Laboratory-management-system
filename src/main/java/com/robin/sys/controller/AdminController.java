package com.robin.sys.controller;

import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.result.Result;
import com.robin.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/list/user")
    public String userList(Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        model.addAttribute("user", user);
        List<User> admins = userService.listAdmin();
        List<User> teachers = userService.listTeacher();
        List<User> students = userService.listStudent();
        model.addAttribute("admins", admins);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        return "user_list";
    }

    @RequestMapping("/delete/user")
    @ResponseBody
    public Result deleteUser(int id, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        userService.deleteUser(id);
        logger.info("管理员："+user.getName()+"，Number："+user.getNumber()+" 删除了用户信息："+id);
        return Result.success("删除成功");
    }
}
