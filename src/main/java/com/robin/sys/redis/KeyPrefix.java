package com.robin.sys.redis;

public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
