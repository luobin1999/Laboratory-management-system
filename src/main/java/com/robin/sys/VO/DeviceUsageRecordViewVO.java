package com.robin.sys.VO;

public class DeviceUsageRecordViewVO {
    //主键ID
    private int id;
    //设备名称
    private String deviceName;
    //设备型号
    private String deviceModel;
    //设备编号
    private String deviceNumber;
    //设备状态
    private int deviceStatus;
    //使用状态
    private int usageStatus;
    //用户姓名
    private String userName;
    //用户账号
    private String userNumber;
    //用户权限
    private int power;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    //目的
    private String target;
    //审核提交时间
    private String createDate;
    //审核状态
    private int status;
    //审核人姓名
    private String reviewerName;
    //审核人账号
    private String reviewerNumber;

    public void setId(int id) {
        this.id = id;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setUsageStatus(int usageStatus) {
        this.usageStatus = usageStatus;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setPower(int power) {
        this.power = power;
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

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public void setReviewerNumber(String reviewerNumber) {
        this.reviewerNumber = reviewerNumber;
    }

    public int getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public int getUsageStatus() {
        return usageStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public int getPower() {
        return power;
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

    public String getCreateDate() {
        return createDate;
    }

    public int getStatus() {
        return status;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewerNumber() {
        return reviewerNumber;
    }

    @Override
    public String toString() {
        return "DeviceUsageRecordViewVO{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", usageStatus=" + usageStatus +
                ", userName='" + userName + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", power=" + power +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", target='" + target + '\'' +
                ", createDate='" + createDate + '\'' +
                ", status=" + status +
                ", reviewerName='" + reviewerName + '\'' +
                ", reviewerNumber='" + reviewerNumber + '\'' +
                '}';
    }
}
