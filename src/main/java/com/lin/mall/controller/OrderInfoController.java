package com.lin.mall.controller;

import com.lin.mall.entity.bo.CreateOrderBo;
import com.lin.mall.service.IOrderInfoService;
import com.lin.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @PostMapping("/createOrder")
    public Result<String> createOrder(@RequestBody CreateOrderBo createOrderBo) {
        orderInfoService.createOrder(createOrderBo);
        return Result.ok();
    }

}
