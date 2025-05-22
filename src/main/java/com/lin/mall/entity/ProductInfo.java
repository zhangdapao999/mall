package com.lin.mall.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@Data
@TableName("product_info")
@ApiModel(value = "ProductInfo对象", description = "商品表")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("商品名")
    private String productName;

    @ApiModelProperty("图片")
    private String picture;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("库存")
    private Integer inventory;

    @ApiModelProperty("0:正常 1:已删除")
    private Integer isDelete;

    @ApiModelProperty("注册时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
