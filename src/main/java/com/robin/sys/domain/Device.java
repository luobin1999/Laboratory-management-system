package com.robin.sys.domain;

import java.util.Date;

public class Device {
    //主键ID
    private int id;
    //设备名称
    private String name;
    //设备型号
    private String model;
    //设备编号
    private String number;
    //设备购买日期
    private Date buyDate;
    //设备录入系统日期
    private Date createDate;
    //设备最近一次修改日期
    private Date updateDate;
    //设备最近一次检查日期
    private Date checkDate;
    //设备状态
    private int deviceStatus;
    //设备负责人
    private String admin;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public String getAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", number='" + number + '\'' +
                ", buyDate=" + buyDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", checkDate=" + checkDate +
                ", deviceStatus=" + deviceStatus +
                ", admin='" + admin + '\'' +
                '}';
    }
}
