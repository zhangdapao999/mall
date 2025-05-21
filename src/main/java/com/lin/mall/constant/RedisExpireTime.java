package com.lin.mall.constant;

/**
 * Redis 缓存过期时间常量类
 * 所有时间单位均为秒
 */
public class RedisExpireTime {

    /**
     * 30分钟（1800秒）
     */
    public static final long THIRTY_MINUTES = 30 * 60;

    /**
     * 1小时（3600秒）
     */
    public static final long ONE_HOUR = 60 * 60;

    /**
     * 1天（86400秒）
     */
    public static final long ONE_DAY = 24 * 60 * 60;

    /**
     * 7天（604800秒）
     */
    public static final long SEVEN_DAYS = 7 * 24 * 60 * 60;

    /**
     * 30天（一个月，2592000秒）
     */
    public static final long THIRTY_DAYS = 30 * 24 * 60 * 60;
}