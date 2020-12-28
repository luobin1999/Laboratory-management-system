package com.robin.sys.VO.experiment;

public class ExperimentClazzViewVO {
    //experiment_record的主键ID
    private int id;
    //实验ID
    private int experimentId;
    //实验编号
    private String experimentNumber;
    //实验名称
    private String experimentName;
    //实验发布时间
    private String createDate;
    //班级名称
    private String clazzName;
    //指导教师
    private String teacherName;
    //班级人数
    private int count;

    public void setId(int id) {
        this.id = id;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public void setExperimentNumber(String experimentNumber) {
        this.experimentNumber = experimentNumber;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public String getExperimentNumber() {
        return experimentNumber;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getClazzName() {
        return clazzName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "ExperimentClazzViewVO{" +
                "id=" + id +
                ", experimentId=" + experimentId +
                ", experimentNumber='" + experimentNumber + '\'' +
                ", experimentName='" + experimentName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", count=" + count +
                '}';
    }
}
