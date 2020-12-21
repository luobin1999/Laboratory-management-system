package com.robin.sys.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Laboratory {
    //实验室ID
    private int id;
    //实验室名称/编号
    private String name;
    //实验室所在校区
    private String campus;
    //实验室负责人
    private String admin;
    //实验室负责人账号
    private String adminNumber;
    //实验室描述
    private String content;
    //实验室创建日期
    private Date createDate;
    //最后一次修改时间
    private Date updateDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCampus() {
        return campus;
    }

    public String getAdmin() {
        return admin;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", campus='" + campus + '\'' +
                ", admin='" + admin + '\'' +
                ", adminNumber='" + adminNumber + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
