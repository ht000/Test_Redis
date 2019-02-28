package com.ht000.test.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class redisService {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 插入数据
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value){
        boolean result = false;
        try{
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 插入带有时间的数据
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key , Object value , Long expireTime){
        boolean result = false;
        try{
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            redisTemplate.expire(key,expireTime, TimeUnit.HOURS);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public Object get(final String key){
        Object result = null;
        ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
        result=operations.get(key);
        return result;
    }



    /**
     * 列表添加
     * @param k
     * @param v
     */
    public boolean lPush(String k,Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.leftPush(k,v);
        return true;
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }
}
