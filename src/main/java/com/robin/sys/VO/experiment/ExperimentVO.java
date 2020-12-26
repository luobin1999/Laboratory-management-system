package com.robin.sys.VO.experiment;

public class ExperimentVO {
    //实验ID
    private int id;
    //实验编号
    private String number;
    //实验名称
    private String name;
    //实验内容简介
    private String content;
    //实验任务书
    private String task;
    //实验创建时间
    private String createDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getTask() {
        return task;
    }

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "ExperimentVO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", task='" + task + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
