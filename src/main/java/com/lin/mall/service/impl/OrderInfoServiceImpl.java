package com.lin.mall.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lin.mall.constant.RedisKey;
import com.lin.mall.entity.OrderInfo;
import com.lin.mall.entity.ProductInfo;
import com.lin.mall.entity.UserInfo;
import com.lin.mall.entity.bo.CreateOrderBo;
import com.lin.mall.enums.ResponseStatusEnum;
import com.lin.mall.enums.UserStatusEnum;
import com.lin.mall.exception.BusinessException;
import com.lin.mall.mapper.OrderInfoMapper;
import com.lin.mall.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.mall.service.IProductInfoService;
import com.lin.mall.service.IUserInfoService;
import com.lin.mall.util.OrderNumberGeneratorUtil;
import com.lin.mall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void createOrder(CreateOrderBo createOrderBo, String token) {
        // 获取用户信息
        String key = String.format(RedisKey.USER_TOKEN, token);
        Object userId = redisUtil.get(key);
        if (userId == null) {
            throw new BusinessException(ResponseStatusEnum.LOGIN_EXPIRED);
        }
        UserInfo userInfo = userInfoService.getById(Long.parseLong(userId.toString()));
        if (userInfo == null) {
            throw new BusinessException(ResponseStatusEnum.USERNAME_NOT_EXIST);
        }
        if (userInfo.getStatus().equals(UserStatusEnum.FROZEN.getCode())) {
            throw new BusinessException(ResponseStatusEnum.USER_FROZEN);
        }
        // 取出商品信息,判断商品库存
        ProductInfo productInfo = productInfoService.getById(createOrderBo.getProductId());
        if (productInfo == null) {
            throw new BusinessException(ResponseStatusEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getInventory() < createOrderBo.getQuantity()) {
            throw new BusinessException(ResponseStatusEnum.PRODUCT_INSUFFICIENT_INVENTORY);
        }
        // 判断用户的余额是否够用
        BigDecimal totalPrice = productInfo.getPrice().multiply(new BigDecimal(createOrderBo.getQuantity()));
        if (userInfo.getBalance().compareTo(totalPrice) < 0) {
            throw new BusinessException(ResponseStatusEnum.INSUFFICIENT_BALANCE);
        }
        // 创建订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(IdUtil.getSnowflakeNextId());
        orderInfo.setOrderSn(OrderNumberGeneratorUtil.generateOrderNumber());
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setTotalPrice(totalPrice);
        orderInfo.setStatus(1);
        orderInfo.setCreatedTime(LocalDateTime.now());
        orderInfo.setPayTime(LocalDateTime.now());
        this.save(orderInfo);
        // 扣减库存
        productInfo.setInventory(productInfo.getInventory() - createOrderBo.getQuantity());
        productInfoService.updateById(productInfo);
        // 扣减用户余额
        userInfo.setBalance(userInfo.getBalance().subtract(totalPrice));
        userInfoService.updateById(userInfo);

    }
}
