package com.lin.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.mall.entity.bo.ProductListBo;
import com.lin.mall.entity.bo.ProductModifyBo;
import com.lin.mall.entity.vo.ProductListVo;
import com.lin.mall.enums.ResponseStatusEnum;
import com.lin.mall.service.IProductInfoService;
import com.lin.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

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

    @PostMapping("/queryList")
    public Result<Page<ProductListVo>> queryList(@RequestBody ProductListBo productListBo) {
        Page<ProductListVo> page = productInfoService.queryList(productListBo);
        return Result.success(page);
    }

    @GetMapping("/deleteById")
    public Result<String> deleteById(@RequestParam(required = false) Long productId) {
        if (productId == null) {
            return Result.error(ResponseStatusEnum.PARAM_MISSING);
        }
        productInfoService.deleteById(productId);
        return Result.ok();
    }


}
