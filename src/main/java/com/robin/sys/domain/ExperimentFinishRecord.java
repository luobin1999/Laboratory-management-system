package com.robin.sys.domain;

import java.util.Date;

public class ExperimentFinishRecord {
    //作业完成记录ID
    private int id;
    //实验ID
    private int experimentId;
    //学生ID
    private int studentId;
    //实验记录ID
    private int experimentRecordId;
    //实验预习
    private String preview;
    //实验预习提交时间
    private Date previewDate;
    //实验报告
    private String report;
    //实验报告提交时间
    private Date reportDate;
    //实验预习成绩
    private int previewScore;
    //实验报告成绩
    private int reportScore;
    //实验总成绩
    private int totalScore;

    public void setId(int id) {
        this.id = id;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setExperimentRecordId(int experimentRecordId) {
        this.experimentRecordId = experimentRecordId;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setPreviewDate(Date previewDate) {
        this.previewDate = previewDate;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public void setPreviewScore(int previewScore) {
        this.previewScore = previewScore;
    }

    public void setReportScore(int reportScore) {
        this.reportScore = reportScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getId() {
        return id;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getExperimentRecordId() {
        return experimentRecordId;
    }

    public String getPreview() {
        return preview;
    }

    public Date getPreviewDate() {
        return previewDate;
    }

    public String getReport() {
        return report;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public int getPreviewScore() {
        return previewScore;
    }

    public int getReportScore() {
        return reportScore;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
