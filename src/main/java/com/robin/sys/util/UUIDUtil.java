package com.robin.sys.util;

import java.util.UUID;

public class UUIDUtil {
    public static String uuid(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}
