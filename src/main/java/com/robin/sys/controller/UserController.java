package com.robin.sys.controller;

import com.robin.sys.VO.clazz.ClazzVO;
import com.robin.sys.VO.login.PasswordVO;
import com.robin.sys.VO.login.UserVO;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
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

import java.util.List;

@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/user/info")
    public String userInfo(Model model, User user){
        if (user == null) {
            return "login";
        }
        List<ClazzVO> classes = clazzService.listClazz();
        model.addAttribute("user", user);
        model.addAttribute("classes", classes);
        return "user_info";
    }

    @RequestMapping("/user/info/update")
    @ResponseBody
    public Result userInfoUpdate(UserVO userVO, Model model, User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (userVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        userService.updateUserInfo(userVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 成功修改个人信息"+userVO);
        return Result.success("修改成功！");
    }

    @RequestMapping("/user/pwd/change")
    public String changePassword(Model model, User user) {
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "change_pwd";
    }

    @RequestMapping("/user/pwd/change/change")
    @ResponseBody
    public Result passwordChange(PasswordVO passwordVO, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+"修改密码"+passwordVO);
        userService.changePassword(passwordVO);
        return Result.success("修改成功");
    }
}
