package com.robin.sys.service;

import com.robin.sys.VO.LoginVO;
import com.robin.sys.VO.RegisterVO;
import com.robin.sys.constant.Constant;
import com.robin.sys.dao.UserDao;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.redis.RedisService;
import com.robin.sys.redis.UserKey;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.util.MD5Util;
import com.robin.sys.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    public boolean login(HttpServletResponse response, LoginVO loginVO){
        if (loginVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String number = loginVO.getNumber();
        String password = loginVO.getPassword();
        int power = loginVO.getPower();
        if (number == null || number == "" || password == null || password == "" || power == 0){
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        User user = userDao.getByNumber(number);
        if(user == null){
            throw new GlobalException(CodeMsg.USER_NOT_FOUND);
        }
        String pwd = MD5Util.formPassToDBPass(password);
        if(!pwd.equals(user.getPassword())){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        user.setLastLoginDate(new Date());
        userDao.updateLastLoginDateById(user);
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    public void addCookie(HttpServletResponse response, String token, User user){
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(Constant.COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void register(RegisterVO registerVO){
        if (registerVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String pwd1 = registerVO.getPassword();
        String pwd2 = registerVO.getPassword1();
        if (!pwd1.equals(pwd2)){
            throw new GlobalException(CodeMsg.PASSWORD_DEFFER);
        }
        User user = new User();
        user.setNumber(registerVO.getNumber());
        user.setSalt(Constant.SALT);
        user.setSex(registerVO.getSex());
        user.setRegisterDate(new Date());
        user.setEmail(registerVO.getEmail());
        user.setClazz(registerVO.getClazz());
        user.setPower(registerVO.getPower());
        user.setName(registerVO.getName());
        String password = MD5Util.formPassToDBPass(registerVO.getPassword());
        user.setPassword(password);
        if (user.getNumber() == null || user.getNumber().length() < 1){
            throw new GlobalException(CodeMsg.NUMBER_EMPTY);
        }
        if (user.getName() == null || user.getName().length() < 1){
            throw new GlobalException(CodeMsg.NAME_EMPTY);
        }
        if (user.getPower() == null || user.getPower() == 0){
            throw new GlobalException(CodeMsg.POWER_EMPTY);
        }
        if (user.getPower() == 1 && (user.getClazz() == null || user.getClazz().length() < 1)){
            throw new GlobalException(CodeMsg.CLAZZ_EMPTY);
        }
        if (user.getSex() == null || user.getSex().length() < 1){
            throw new GlobalException(CodeMsg.SEX_EMPTY);
        }
        if (user.getPassword() == null || user.getPassword().length() < 1){
            throw new GlobalException(CodeMsg.PASSWORD_EMPTY);
        }
        if (user.getEmail() == null || user.getEmail().length() < 1){
            throw new GlobalException(CodeMsg.EMAIL_EMPTY);
        }
        userDao.insertUser(user);

        logger.info(registerVO+"----注册成功！");
    }

    /*public int addTest(){
        User user = new User();
        user.setClazz("计算机1701班");
        user.setEmail("9999@qq.com");
        user.setLastLoginDate(new Date());
        user.setName("张三");
        user.setNumber("123456789");
        user.setPassword("asdasvvx");
        user.setPower(1);
        user.setRegisterDate(new Date());
        user.setSalt("123456");
        user.setSex("男");
        return userDao.insertUser(user);
    }

    public User testGetById(Long id){
        return userDao.getById(id);
    }

    public User testGetByNumber(String number){
        return userDao.getByNumber(number);
    }

    public String testUpdateById(User user){
        userDao.updatePwdById(user);
        return "success";
    }

    public String testUpdateByNumber(User user){
        userDao.updatePwdByNumber(user);
        return "success";
    }*/
}
