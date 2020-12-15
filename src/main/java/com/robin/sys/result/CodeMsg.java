package com.robin.sys.result;

public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
    public static CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500103, "请求太频繁");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    //登录注册
    public static CodeMsg PASSWORD_DEFFER = new CodeMsg(500201, "两次密码不一致");
    public static CodeMsg REGISTER_ERROR = new CodeMsg(500202, "注册失败");
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
