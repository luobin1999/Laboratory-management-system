package com.robin.sys.VO.clazz;

public class PreClazzVO {
    private int id;
    private String grade;
    private String clazz;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getGrade() {
        return grade;
    }

    public String getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "PreClazzVO{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
