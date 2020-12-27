package com.robin.sys.result;

public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg CLIENT_ERROR = new CodeMsg(500101, "客户端异常");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    //登录注册
    public static CodeMsg PASSWORD_DEFFER = new CodeMsg(500201, "两次密码不一致");
    public static CodeMsg USER_NOT_FOUND = new CodeMsg(500203, "用户不存在");
    public static CodeMsg NAME_EMPTY = new CodeMsg(500204, "姓名不能为空");
    public static CodeMsg CLAZZ_EMPTY = new CodeMsg(500205, "班级不能为空");
    public static CodeMsg EMAIL_EMPTY = new CodeMsg(500206, "EMAIL不能为空");
    public static CodeMsg NUMBER_EMPTY = new CodeMsg(500207, "账号不能为空");
    public static CodeMsg SEX_EMPTY = new CodeMsg(500208, "性别不能为空");
    public static CodeMsg POWER_EMPTY = new CodeMsg(500209, "权限不能为空");
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "身份验证已过期，请重新登录");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
    //班级管理
    public static CodeMsg GRADE_EMPTY = new CodeMsg(500301, "年级字段不能为空");
    public static CodeMsg CLAZZ_CLAZZ_EMPTY = new CodeMsg(500302, "班级字段不能为空");
    public static CodeMsg REPEAT_ADD = new CodeMsg(500303, "不能重复添加");
    //更改密码
    public static CodeMsg PASSWORD_DIFFER = new CodeMsg(500400, "两次密码不一致");
    //权限管理
    public static CodeMsg POWER_LOW = new CodeMsg(500500, "权限不足");
    public static CodeMsg POWER_ERROR = new CodeMsg(500500, "权限不匹配");
    //实验室管理
    public static CodeMsg LABORATORY_NAME_EMPTY = new CodeMsg(500601, "实验室名称不能为空");
    public static CodeMsg LABORATORY_CAMPUS_EMPTY = new CodeMsg(500601, "所在校区不能为空");
    //设备管理
    public static CodeMsg DEVICE_NAME_EMPTY = new CodeMsg(500701, "设备名称不能为空");
    public static CodeMsg DEVICE_MODEL_EMPTY = new CodeMsg(500702, "设备型号不能为空");
    public static CodeMsg DEVICE_NUMBER_EMPTY = new CodeMsg(500703, "设备编号不能为空");
    public static CodeMsg DEVICE_BUY_DATE_EMPTY = new CodeMsg(500704, "设备购买日期不能为空");
    //预约管理
    public static CodeMsg START_END_DATE_EMPTY = new CodeMsg(500801, "开始/结束时间不能为空");
    public static CodeMsg START_END_DATE_ERROR = new CodeMsg(500802, "开始/结束时间有误");
    public static CodeMsg TARGET_EMPTY = new CodeMsg(500803, "预约目的不能为空");
    public static CodeMsg BORROW_TIME_CLASH = new CodeMsg(500804, "这个时段已有其他用户预约");
    public static CodeMsg TIME_FORMAT_ERROR = new CodeMsg(500805, "时间格式错误");
    public static CodeMsg OVER_BORROW_DATE = new CodeMsg(500806, "最多可以提前7天预约");
    public static CodeMsg OVER_BORROW_PAR_DATE = new CodeMsg(500807, "每次预约时间最长1天");
    //MinIO异常
    public static CodeMsg FILE_UPLOAD_ERROR = new CodeMsg(500900, "文件上传出错");
    public static CodeMsg FILE_DOWNLOAD_ERROR = new CodeMsg(500901, "文件下载出错");
    public static CodeMsg STREAM_CLOSE_ERROR = new CodeMsg(500902, "文件流关闭出错");
    public static CodeMsg FILE_EMPTY = new CodeMsg(500903, "文件不能为空");
    //实验管理模块
    public static CodeMsg EXPERIMENT_NUMBER_EMPTY = new CodeMsg(600101, "实验编号不能为空");
    public static CodeMsg EXPERIMENT_NAME_EMPTY = new CodeMsg(600102, "实验名称不能为空");
    public static CodeMsg EXPERIMENT_CONTENT_EMPTY = new CodeMsg(600103, "实验内容简介不能为空");
    //作业模块
    public static CodeMsg PLEASE_SUBMIT_TASK_FIRST = new CodeMsg(600201, "请先完成作业");

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    private CodeMsg(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
