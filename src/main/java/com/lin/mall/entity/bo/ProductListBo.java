package com.lin.mall.entity.bo;

import lombok.Data;

@Data
public class ProductListBo {

    private Integer page;

    private Integer pageSize;

    private String keyword;

}
