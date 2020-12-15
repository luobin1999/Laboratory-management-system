package com.robin.sys.access;

import com.robin.sys.domain.User;

public class UserContext {
    private static ThreadLocal<User> userHodler = new ThreadLocal<User>();

    public static User getUser(){
        return userHodler.get();
    }

    public static void setUser(User user){
        userHodler.set(user);
    }
}
