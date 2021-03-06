package com.robin.sys.VO.laboratory;

public class PreLaboratoryVO {
    private int id;
    private String name;
    private String campus;
    private String admin;
    private String adminNumber;
    private String content;

    @Override
    public String toString() {
        return "PreLaboratoryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", campus='" + campus + '\'' +
                ", admin='" + admin + '\'' +
                ", adminNumber='" + adminNumber + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

}
