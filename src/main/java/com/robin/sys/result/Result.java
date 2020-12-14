package com.robin.sys.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static<T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public static<T>  Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    /**
     * 成功时调用
     * @param data
     */
    private Result(T data){
        this.code=0;
        this.data=data;
        this.msg="success";
    }

    /**
     * 失败时调用
     * @param codeMsg
     */
    private Result(CodeMsg codeMsg){
        if (codeMsg == null){
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
