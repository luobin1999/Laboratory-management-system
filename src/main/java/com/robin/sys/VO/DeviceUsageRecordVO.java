package com.robin.sys.VO;

public class DeviceUsageRecordVO {
    //主键ID
    private int id;
    //设备ID
    private int deviceId;
    //用户ID
    private int userId;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    //目的
    private String target;
    //审核状态；1审核中，2使用中，3审核不通过，4已完成
    private int status;
    //审核人ID
    private int reviewerId;
    //审核提交时间
    private String createDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
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

    public int getDeviceId() {
        return deviceId;
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
        return "DeviceUsageRecordVO{" +
                "id=" + id +
                ", deviceId=" + deviceId +
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
