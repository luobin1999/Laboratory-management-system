package com.robin.sys.domain;

import java.util.Date;

public class ExperimentRecord {
    //主键ID
    private int id;
    //实验ID
    private int experimentId;
    //班级名称
    private String clazzName;
    //指导教师ID
    private int teacherId;
    //创建时间
    private Date createDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
