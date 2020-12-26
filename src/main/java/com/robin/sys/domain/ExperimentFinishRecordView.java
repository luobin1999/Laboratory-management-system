package com.robin.sys.domain;

import java.util.Date;

public class ExperimentFinishRecordView {
    //实验完成记录ID
    private int id;
    //实验ID
    private int exprimentId;
    //实验编号
    private String experimentNumber;
    //实验名称
    private String experimentName;
    //实验任务书
    private String experimentTask;
    //实验发布时间
    private Date createDate;
    //任课教师名字
    private String teacherName;
    //预习作业
    private String preview;
    //报告作业
    private String report;
    //总成绩
    private int totalScore;

    public void setId(int id) {
        this.id = id;
    }

    public void setExprimentId(int exprimentId) {
        this.exprimentId = exprimentId;
    }

    public void setExperimentNumber(String experimentNumber) {
        this.experimentNumber = experimentNumber;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public void setExperimentTask(String experimentTask) {
        this.experimentTask = experimentTask;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getId() {
        return id;
    }

    public int getExprimentId() {
        return exprimentId;
    }

    public String getExperimentNumber() {
        return experimentNumber;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getExperimentTask() {
        return experimentTask;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getPreview() {
        return preview;
    }

    public String getReport() {
        return report;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
