package com.robin.sys.VO.experiment;

public class ExperimentFinishRecordViewVO {
    //实验完成记录ID
    private int id;
    //实验ID
    private int exprimentId;
    //学生姓名
    private String studentName;
    //学生账号
    private String studentNumber;
    //实验编号
    private String experimentNumber;
    //实验名称
    private String experimentName;
    //实验任务书
    private String experimentTask;
    //实验发布时间
    private String createDate;
    //任课教师名字
    private String teacherName;
    //预习作业
    private String preview;
    //报告作业
    private String report;
    //预习提交时间
    private String previewDate;
    //报告提交时间
    private String reportDate;
    //预习成绩
    private int previewScore;
    //报告成绩
    private int reportScore;
    //总成绩
    private int totalScore;

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

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

    public void setCreateDate(String createDate) {
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

    public void setPreviewDate(String previewDate) {
        this.previewDate = previewDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public void setPreviewScore(int previewScore) {
        this.previewScore = previewScore;
    }

    public void setReportScore(int reportScore) {
        this.reportScore = reportScore;
    }

    public String getPreviewDate() {
        return previewDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public int getPreviewScore() {
        return previewScore;
    }

    public int getReportScore() {
        return reportScore;
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

    public String getCreateDate() {
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

    @Override
    public String toString() {
        return "ExperimentFinishRecordViewVO{" +
                "id=" + id +
                ", exprimentId=" + exprimentId +
                ", studentName='" + studentName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", experimentNumber='" + experimentNumber + '\'' +
                ", experimentName='" + experimentName + '\'' +
                ", experimentTask='" + experimentTask + '\'' +
                ", createDate='" + createDate + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", preview='" + preview + '\'' +
                ", report='" + report + '\'' +
                ", previewDate='" + previewDate + '\'' +
                ", reportDate='" + reportDate + '\'' +
                ", previewScore=" + previewScore +
                ", reportScore=" + reportScore +
                ", totalScore=" + totalScore +
                '}';
    }
}
