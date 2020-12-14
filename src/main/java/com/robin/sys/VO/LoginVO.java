package com.robin.sys.VO;

public class LoginVO {
    private String number;
    private String password;
    private int power;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                '}';
    }
}
