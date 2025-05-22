package com.lin.mall.entity.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModifyBo {

    private Long id;

    @ApiModelProperty("商品名")
    private String productName;

    @ApiModelProperty("图片")
    private String picture;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("库存")
    private Integer inventory;

}
