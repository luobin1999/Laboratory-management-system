package com.robin.sys.util;

import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
    public static String uuid(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
    }
}
