package com.robin.sys.util;

import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;

public class PowerUtil {

    public static void PowerCheck23(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
    }

    public static void PowerCheck12(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
    }

    public static void PowerCheck1(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 1) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
    }

    public static void PowerCheck2(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
    }

    public static void PowerCheck3(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
    }
}
