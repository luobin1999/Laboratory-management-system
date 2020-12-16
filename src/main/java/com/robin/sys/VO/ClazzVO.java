package com.robin.sys.VO;

public class ClazzVO {
    //班级ID
    private int id;
    //班级所在年级
    private String grade;
    //班级名称
    private String clazz;
    //创建时间
    private String createDate;
    //修改时间
    private String updateDate;
    //班级总人数
    private int count;

    public void setId(int id) {
        this.id = id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public int getCount() {
        return count;
    }
}
