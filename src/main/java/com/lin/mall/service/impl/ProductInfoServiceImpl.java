package com.lin.mall.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.mall.entity.ProductInfo;
import com.lin.mall.entity.bo.ProductListBo;
import com.lin.mall.entity.bo.ProductModifyBo;
import com.lin.mall.entity.vo.ProductListVo;
import com.lin.mall.enums.DeleteEnum;
import com.lin.mall.enums.ResponseStatusEnum;
import com.lin.mall.exception.BusinessException;
import com.lin.mall.mapper.ProductInfoMapper;
import com.lin.mall.service.IProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@Slf4j
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {

    @Override
    public void modify(ProductModifyBo productModifyBo) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productModifyBo, productInfo);

        if (productModifyBo.getId() == null) {
            productInfo.setId(IdUtil.getSnowflakeNextId());
            productInfo.setCreatedTime(LocalDateTime.now());
            productInfo.setIsDelete(DeleteEnum.NOT_DELETE.getCode());
        } else {
            productInfo.setUpdatedTime(LocalDateTime.now());
        }
        try {
            this.saveOrUpdate(productInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(ResponseStatusEnum.SYSTEM_ERROR);
        }

    }

    @Override
    public Page<ProductListVo> queryList(ProductListBo productListBo) {
        Page<ProductListVo> page = new Page<>(productListBo.getPage(), productListBo.getPageSize());
        this.baseMapper.queryList(page, productListBo);
        return page;
    }

    @Override
    public void deleteById(Long productId) {
        LambdaUpdateWrapper<ProductInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ProductInfo::getId, productId)
                .set(ProductInfo::getIsDelete, DeleteEnum.IS_DELETED.getCode());
        boolean result = this.update(updateWrapper);
        if (!result) {
            throw new BusinessException(ResponseStatusEnum.PRODUCT_NOT_EXIST);
        }
    }
}
