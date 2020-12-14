package com.robin.sys.controller;

import com.robin.sys.VO.LoginVO;
import com.robin.sys.VO.RegisterVO;
import com.robin.sys.result.Result;
import com.robin.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/register")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result doLogin(HttpServletResponse response, LoginVO loginVO){
        logger.info(loginVO.toString());
        userService.login(response, loginVO);
        return Result.success("success");
    }

    @RequestMapping("/do_register")
    @ResponseBody
    public Result doRegister(RegisterVO registerVO){
        logger.info(registerVO.toString());
        userService.register(registerVO);
        return Result.success("success");
    }
    /*@RequestMapping("/addTest")
    @ResponseBody
    public int addTest(){
        return userService.addTest();
    }

    @RequestMapping("/getById")
    @ResponseBody
    public User testGetById(){
        return userService.testGetById(1L);
    }

    @RequestMapping("/getByNumber")
    @ResponseBody
    public User testGetByNumber(){
        return userService.testGetByNumber("123456789");
    }

    @RequestMapping("/updateById")
    @ResponseBody
    public String testUpdateById(){
        User user = new User();
        user.setId(1L);
        user.setPassword("abcdefg");
        return userService.testUpdateById(user);
    }

    @RequestMapping("/updateByNumber")
    @ResponseBody
    public String testUpdateByNumber(){
        User user = new User();
        user.setNumber("123456789");
        user.setPassword("123456");
        return userService.testUpdateByNumber(user);
    }*/
}
