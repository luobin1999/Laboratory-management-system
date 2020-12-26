package com.robin.sys.service;

import com.robin.sys.VO.login.LoginVO;
import com.robin.sys.VO.login.PasswordVO;
import com.robin.sys.VO.login.RegisterVO;
import com.robin.sys.VO.login.UserVO;
import com.robin.sys.access.UserContext;
import com.robin.sys.constant.Constant;
import com.robin.sys.dao.UserDao;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.redis.RedisService;
import com.robin.sys.redis.UserKey;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.util.MD5Util;
import com.robin.sys.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    @Transactional
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
        User user = userDao.getByNumber(number, power);
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

    public void logout(HttpServletRequest request){
        String paramToken = request.getParameter(Constant.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, Constant.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        boolean exist = redisService.exist(UserKey.token, token);
        if (!exist) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        redisService.delete(UserKey.token, token);
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

    public User getUserByToken(HttpServletResponse response, String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    private String getCookieValue(HttpServletRequest request,String cookiename){
        Cookie[] cookies=request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for (Cookie cookie:cookies){
            if(cookie.getName().equals(cookiename)){
                return cookie.getValue();
            }
        }
        return null;
    }

    @Transactional
    public void  updateUserInfo(UserVO userVO) {
        int id = userVO.getId();
        String name = userVO.getName();
        String clazz = userVO.getClazz();
        String email = userVO.getEmail();
        String sex = userVO.getSex();
        if (StringUtils.isEmpty(name)){
            throw new GlobalException(CodeMsg.NAME_EMPTY);
        }
        if (StringUtils.isEmpty(email)) {
            throw new GlobalException(CodeMsg.EMAIL_EMPTY);
        }
        if (StringUtils.isEmpty(sex)) {
            throw new GlobalException(CodeMsg.SEX_EMPTY);
        }
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setClazz(clazz);
        user.setEmail(email);
        user.setSex(sex);
        userDao.updateUserInfoById(user);
        user = userDao.getByIdWithOutPower(user.getId());
        UserContext.setUser(user);
    }

    @Transactional
    public void changePassword(PasswordVO passwordVO) {
        if (passwordVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        int id = passwordVO.getId();
        String password1 = passwordVO.getPassword1();
        String password2 = passwordVO.getPassword2();
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        if (password1 == null || password1.length() <1){
            throw new GlobalException(CodeMsg.PASSWORD_EMPTY);
        }
        if (password2 == null || password2.length() <1){
            throw new GlobalException(CodeMsg.PASSWORD_EMPTY);
        }
        if (!password1.equals(password2)) {
            throw new GlobalException(CodeMsg.PASSWORD_DIFFER);
        }
        User user = new User();
        user.setId(id);
        user.setPassword(MD5Util.formPassToDBPass(password1));
        userDao.changePasswordById(user);
    }

    public List<User> listAdmin(){
        return userDao.listAdmin();
    }

    public List<User> listTeacher(){
        return userDao.listTeacher();
    }

    public List<User> listStudent(){
        return userDao.listStudent();
    }

    @Transactional
    public int deleteUser(int id) {
        return userDao.deleteUserById(id);
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
