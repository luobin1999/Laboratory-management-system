package com.robin.sys.VO.laboratory;

public class PreLaboratoryRecordVO {
    private int id;
    private int laboratoryId;
    private int userId;
    private String startDate;
    private String endDate;
    private String target;
    private int status;
    private int reviewerId;
    private String createDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
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

    public void setCreateDate(String createDate) {
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
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

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "PreLaboratoryRecordVO{" +
                "id=" + id +
                ", laboratoryId=" + laboratoryId +
                ", userId=" + userId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", target='" + target + '\'' +
                ", status=" + status +
                ", reviewerId=" + reviewerId +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
