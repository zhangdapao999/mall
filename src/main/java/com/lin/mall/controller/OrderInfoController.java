package com.lin.mall.controller;

import com.lin.mall.entity.bo.CreateOrderBo;
import com.lin.mall.service.IOrderInfoService;
import com.lin.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
    public Result<String> createOrder(@RequestBody CreateOrderBo createOrderBo,
                                      @RequestHeader String token) {
        orderInfoService.createOrder(createOrderBo, token);
        return Result.ok();
    }

}
