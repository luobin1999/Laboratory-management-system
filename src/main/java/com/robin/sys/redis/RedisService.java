package com.robin.sys.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
    @Autowired
    private JedisPool jedisPool;

  /*  public void  testSet(String key,String value){
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        return2Pool(jedis);
    }
*/
    /**
     * 获取对象
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            return2Pool(jedis);
        }
    }

    /**
     * 添加对象
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() < 1){
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            return2Pool(jedis);
        }
    }

    /**
     * 判断key是否存在
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exist(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            return2Pool(jedis);
        }
    }

    /**
     * 删除对象
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean delete(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret > 0;
        } finally {
            return2Pool(jedis);
        }
    }

    public static  <T> T stringToBean(String str, Class<T> clazz) {
        if(str==null||str.length()<1||clazz==null){
            return null;
        }
        if(clazz==int.class||clazz==Integer.class){
            return (T)Integer.valueOf(str);
        } else if(clazz==Long.class || clazz==long.class){
            return (T)Long.valueOf(str);
        } else if(clazz==String.class){
            return (T)str;
        } else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    public static  <T> String beanToString(T value) {
        if(value==null){
            return null;
        }
        if(value.getClass()==Integer.class || value.getClass()==int.class){
            return value+"";
        } else if(value.getClass()==long.class || value.getClass()==Long.class){
            return value+"";
        } else if(value.getClass()==String.class){
            return (String)value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    private void return2Pool(Jedis jedis){
        if (jedis != null) {
            jedis.close();
        }
    }
}
