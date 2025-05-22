package com.lin.mall.service.impl;

import com.lin.mall.entity.OrderInfo;
import com.lin.mall.entity.bo.CreateOrderBo;
import com.lin.mall.mapper.OrderInfoMapper;
import com.lin.mall.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public void createOrder(CreateOrderBo createOrderBo) {
        // 取出商品信息



    }
}
