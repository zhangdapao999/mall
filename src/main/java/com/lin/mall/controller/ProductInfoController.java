package com.lin.mall.controller;

import com.lin.mall.entity.bo.ProductModifyBo;
import com.lin.mall.service.IProductInfoService;
import com.lin.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Autowired
    private IProductInfoService productInfoService;

    @PostMapping("/modify")
    public Result<String> modify(@RequestBody ProductModifyBo productModifyBo) {
        productInfoService.modify(productModifyBo);
        return Result.ok();
    }


}
