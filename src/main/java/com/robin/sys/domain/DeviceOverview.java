package com.robin.sys.domain;

import java.util.Date;

public class DeviceOverview {
    //主键ID
    private int id;
    //设备名称
    private String name;
    //设备型号
    private String model;
    //设备总数
    private int count;
    //正常设备的数量
    private int normalCount;
    //等待维修设备的数量
    private int waitRepairCount;
    //已经维修完毕的设备数量
    private int alreadyRepairCount;
    //报废设备的数量
    private int scrapCount;
    //空闲中的设备数量
    private int freeCount;
    //使用中的设备数量
    private int usageCount;
    //最近一次检查的日期
    private Date checkDate;
    //设备负责人
    private String admin;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNormalCount(int normalCount) {
        this.normalCount = normalCount;
    }

    public void setWaitRepairCount(int waitRepairCount) {
        this.waitRepairCount = waitRepairCount;
    }

    public void setAlreadyRepairCount(int alreadyRepairCount) {
        this.alreadyRepairCount = alreadyRepairCount;
    }

    public void setScrapCount(int scrapCount) {
        this.scrapCount = scrapCount;
    }

    public void setFreeCount(int freeCount) {
        this.freeCount = freeCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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

    public int getNormalCount() {
        return normalCount;
    }

    public int getWaitRepairCount() {
        return waitRepairCount;
    }

    public int getAlreadyRepairCount() {
        return alreadyRepairCount;
    }

    public int getScrapCount() {
        return scrapCount;
    }

    public int getFreeCount() {
        return freeCount;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public String getAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "DeviceOverview{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", count=" + count +
                ", normalCount=" + normalCount +
                ", waitRepairCount=" + waitRepairCount +
                ", alreadyRepairCount=" + alreadyRepairCount +
                ", scrapCount=" + scrapCount +
                ", freeCount=" + freeCount +
                ", usageCount=" + usageCount +
                ", checkDate=" + checkDate +
                ", admin='" + admin + '\'' +
                '}';
    }
}
