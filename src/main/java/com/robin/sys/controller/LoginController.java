package com.robin.sys.controller;

import com.robin.sys.VO.clazz.ClazzVO;
import com.robin.sys.VO.login.LoginVO;
import com.robin.sys.VO.login.RegisterVO;
import com.robin.sys.domain.User;
import com.robin.sys.result.Result;
import com.robin.sys.service.ClazzService;
import com.robin.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/register")
    public String toRegister(Model model){
        List<ClazzVO> classes = clazzService.listClazz();
        model.addAttribute("classes", classes);
        return "register";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result doLogin(HttpServletResponse response, @Valid LoginVO loginVO){
        logger.info(loginVO.toString());
        userService.login(response, loginVO);
        logger.info("登录成功");
        return Result.success(true);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, User user){
        userService.logout(request);
        logger.info("用户："+user.getName()+",Number:"+user.getNumber()+"，退出登录！");
        return toLogin();
    }

    @RequestMapping("/do_register")
    @ResponseBody
    public Result doRegister(RegisterVO registerVO){
        logger.info(registerVO.toString());
        userService.register(registerVO);
        return Result.success(true);
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
