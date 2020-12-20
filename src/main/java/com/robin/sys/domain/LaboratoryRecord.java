package com.robin.sys.domain;

import java.util.Date;

public class LaboratoryRecord {
    private int id;
    private int laboratoryId;
    private int userId;
    private Date startDate;
    private Date endDate;
    private String target;
    private int status;
    private int reviewerId;
    private Date createDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTarget() {
        return target;
    }

    public int getStatus() {
        return status;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "LaboratoryRecord{" +
                "id=" + id +
                ", laboratoryId=" + laboratoryId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", target='" + target + '\'' +
                ", status=" + status +
                ", reviewerId=" + reviewerId +
                ", createDate=" + createDate +
                '}';
    }
}
