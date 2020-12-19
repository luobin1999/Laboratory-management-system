package com.robin.sys.VO;

import java.util.Date;

public class PreDeviceVO {
    //主键ID
    private int id;
    //设备名称
    private String name;
    //设备型号
    private String model;
    //设备编号
    private String number;
    //设备购买日期
    private String buyDate;
    //设备负责人
    private String admin;
    //设备状态；0正常使用，1等待维修，2维修完成，3已报废
    private int deviceStatus;
    //使用状态；0空闲中，1使用中
    private int usageStatus;

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

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setUsageStatus(int usageStatus) {
        this.usageStatus = usageStatus;
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

    public String getBuyDate() {
        return buyDate;
    }

    public String getAdmin() {
        return admin;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public int getUsageStatus() {
        return usageStatus;
    }

    @Override
    public String toString() {
        return "PreDeviceVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", number='" + number + '\'' +
                ", buyDate=" + buyDate +
                ", admin='" + admin + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", usageStatus=" + usageStatus +
                '}';
    }
}
