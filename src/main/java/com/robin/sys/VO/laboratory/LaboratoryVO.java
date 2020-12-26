package com.robin.sys.VO.laboratory;

public class LaboratoryVO {
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
    private String createDate;
    //最后一次修改时间
    private String updateDate;

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

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
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

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    @Override
    public String toString() {
        return "LaboratoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", campus='" + campus + '\'' +
                ", admin='" + admin + '\'' +
                ", adminNumber='" + adminNumber + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
