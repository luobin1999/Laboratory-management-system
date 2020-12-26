package com.robin.sys.VO.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RegisterVO {
    //姓名
    @NotNull
    private String name;
    //性别
    @NotNull
    private String sex;
    //电子邮件
    @NotNull
    @Email
    private String email;
    //班级
    private String clazz;
    //账号 or 学号
    @NotNull
    private String number;
    //密码
    @NotNull
    private String password;
    //确认密码
    @NotNull
    private String password1;
    //权限，1学生，2老师，3管理员
    @NotNull
    private Integer power;

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

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPower(Integer power) {
        this.power = power;
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

    public String getPassword1() {
        return password1;
    }

    public Integer getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "RegisterVO{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", clazz='" + clazz + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", password1='" + password1 + '\'' +
                ", power=" + power +
                '}';
    }
}
