package com.lin.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.mall.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.mall.entity.bo.ProductListBo;
import com.lin.mall.entity.bo.ProductModifyBo;
import com.lin.mall.entity.vo.ProductListVo;

import java.util.List;

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

    /**
     * 分页查询商品信息
     * @param productListBo 查询参数
     */
    Page<ProductListVo> queryList(ProductListBo productListBo);

    /**
     * 删除商品
     * @param productId 商品id
     */
    void deleteById(Long productId);
}
