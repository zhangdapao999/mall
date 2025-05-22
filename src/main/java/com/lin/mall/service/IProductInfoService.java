package com.lin.mall.service;

import com.lin.mall.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.mall.entity.bo.ProductModifyBo;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
public interface IProductInfoService extends IService<ProductInfo> {

    /**
     * 新增/修改商品信息
     */
    void modify(ProductModifyBo productModifyBo);
}
