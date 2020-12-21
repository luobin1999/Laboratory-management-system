package com.robin.sys.domain;

import java.util.Date;

public class LaboratoryUsageRecord {
    //审核信息ID
    private int id;
    //实验室名称
    private String laboratoryName;
    //实验室所在校区
    private String campus;
    //预约人姓名
    private String userName;
    //预约人账号
    private String userNumber;
    //预约人身份
    private int power;
    //预约开始时间
    private Date startDate;
    //预约结束时间
    private Date endDate;
    //预约目的
    private String target;
    //申请创建时间
    private Date createDate;
    //审核状态；1审核中，2使用中，3审核不通过，4已完成
    private int status;
    //审核人姓名
    private String reviewerName;
    //审核人账号
    private String reviewerNumber;

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCampus() {
        return campus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public int getPower() {
        return power;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTarget() {
        return target;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getStatus() {
        return status;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewerNumber() {
        return reviewerNumber;
    }
}
