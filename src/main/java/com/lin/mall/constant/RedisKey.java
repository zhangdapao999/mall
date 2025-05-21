package com.lin.mall.constant;

/**
 * Redis Key 常量类
 * 所有Key定义遵循统一的命名规范和格式
 */
public class RedisKey {

    /**
     * 用户信息缓存前缀
     * 完整格式: user:{userId}
     */
    public static final String USER_INFO = "user:%s";

    /**
     * 用户令牌缓存前缀
     * 完整格式: token:{token}
     */
    public static final String USER_TOKEN = "token:%s";

    /**
     * 短信验证码缓存前缀
     * 完整格式: sms:{mobile}:{type}
     */
    public static final String SMS_CODE = "sms:%s:%s";

    /**
     * 热门商品排行榜
     * 完整格式: rank:hot_products
     */
    public static final String HOT_PRODUCTS_RANK = "rank:hot_products";

    /**
     * 用户购物车缓存前缀
     * 完整格式: cart:{userId}
     */
    public static final String SHOPPING_CART = "cart:%s";

    /**
     * 系统配置缓存
     * 完整格式: config:{configKey}
     */
    public static final String SYSTEM_CONFIG = "config:%s";

    /**
     * 限流计数器前缀
     * 完整格式: rate_limit:{ip}:{api}
     */
    public static final String RATE_LIMIT = "rate_limit:%s:%s";
}