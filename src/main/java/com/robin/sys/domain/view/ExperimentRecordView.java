package com.robin.sys.domain.view;

public class ExperimentRecordView {
    private int id;
    private String experimentName;
    private String experimentNumber;
    private int experimentId;
    private String viewName;

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public void setExperimentNumber(String experimentNumber) {
        this.experimentNumber = experimentNumber;
    }

    public int getId() {
        return id;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getExperimentNumber() {
        return experimentNumber;
    }

    @Override
    public String toString() {
        return "ExperimentRecordView{" +
                "id=" + id +
                ", experimentName='" + experimentName + '\'' +
                ", experimentNumber='" + experimentNumber + '\'' +
                ", experimentId=" + experimentId +
                ", viewName='" + viewName + '\'' +
                '}';
    }
}
