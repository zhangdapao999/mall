package com.lin.mall.service;

import com.lin.mall.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.mall.entity.bo.CreateOrderBo;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 创建订单
     */
    void createOrder(CreateOrderBo createOrderBo);
}
