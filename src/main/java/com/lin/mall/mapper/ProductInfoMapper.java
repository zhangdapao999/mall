package com.lin.mall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.mall.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.mall.entity.bo.ProductListBo;
import com.lin.mall.entity.vo.ProductListVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

    Page<ProductListVo> queryList(@Param("page") Page<ProductListVo> page,
                   @Param("productListBo") ProductListBo productListBo);
}
