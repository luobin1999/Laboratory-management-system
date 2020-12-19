package com.robin.sys.VO;

public class DeviceVO {
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
    //设备录入系统日期
    private String createDate;
    //设备最近一次修改日期
    private String updateDate;
    //设备最近一次检查日期
    private String checkDate;
    //设备状态
    private int deviceStatus;
    //当前使用状态
    private int usageStatus;
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

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setUsageStatus(int usageStatus) {
        this.usageStatus = usageStatus;
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

    public String getBuyDate() {
        return buyDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public int getUsageStatus() {
        return usageStatus;
    }

    public String getAdmin() {
        return admin;
    }
}
