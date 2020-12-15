package com.robin.sys.redis;

public class LoginKey extends BasePrefix{
    private LoginKey(int expireSeconds, String prefix){
        super(expireSeconds, prefix);
    }
    public static LoginKey getLoginHtml = new LoginKey(0, "login");
}
