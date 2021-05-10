package com.robin.sys.domain;

import com.excel.poi.annotation.ExportField;
import com.excel.poi.annotation.ImportField;

public class GroupInfo {
    //主键ID
    private Integer id;
    //学生学号
    @ExportField(columnName = "学号")
    @ImportField(required = true)
    private String studentNumber;
    //学生姓名
    @ExportField(columnName = "姓名")
    @ImportField(required = true)
    private String studentName;
    //班级名称
    @ExportField(columnName = "班级")
    @ImportField(required = true)
    private String clazzName;
    //实验名称
    @ExportField(columnName = "实验名称")
    @ImportField(required = true)
    private String experimentName;
    //分组编号
    @ExportField(columnName = "组号")
    @ImportField(required = true)
    private String groupId;
    //是否是组长
    @ExportField(columnName = "是否组长", defaultCellValue = "0")
    @ImportField(required = true)
    private Integer isLeader;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    public Integer getId() {
        return id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getGroupId() {
        return groupId;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", experimentName='" + experimentName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", isLeader=" + isLeader +
                '}';
    }
}
