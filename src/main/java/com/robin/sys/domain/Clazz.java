package com.robin.sys.domain;

import java.util.Date;

public class Clazz {
    //班级ID
    private int id;
    //班级所在年级
    private String grade;
    //班级名称
    private String clazz;
    //创建时间
    private Date createDate;
    //修改时间
    private Date updateDate;
    //班级总人数
    private int count;

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public String getClazz() {
        return clazz;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getCount() {
        return count;
    }
}
