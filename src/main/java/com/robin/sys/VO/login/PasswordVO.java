package com.robin.sys.VO.login;

public class PasswordVO {
    private int id;
    private String number;
    private String password1;
    private String password2;

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    @Override
    public String toString() {
        return "PasswordVO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
