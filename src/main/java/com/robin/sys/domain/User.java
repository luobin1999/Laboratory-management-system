package com.robin.sys.domain;

import java.util.Date;

public class User {
    //主键ID
    private Long id;
    //姓名
    private String name;
    //性别
    private String sex;
    //电子邮件
    private String email;
    //班级
    private String clazz;
    //账号 or 学号
    private String number;
    //密码
    private String password;
    //加密串
    private String salt;
    //注册日期
    private Date registerDate;
    //最后一次登录时间
    private Date lastLoginDate;
    //权限，1学生，2老师，3管理员
    private Integer power;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String getClazz() {
        return clazz;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public Integer getPower() {
        return power;
    }
}
