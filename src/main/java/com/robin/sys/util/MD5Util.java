package com.robin.sys.util;

import com.robin.sys.constant.Constant;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassToFormPass(String inputPass){
        String salt = Constant.SALT;
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(4) + salt.charAt(6);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass) {
        String salt = Constant.SALT;
        String str = "" + salt.charAt(1) + salt.charAt(3)+ formPass + salt.charAt(5) + salt.charAt(7);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass);
        return dbPass;
    }
}
