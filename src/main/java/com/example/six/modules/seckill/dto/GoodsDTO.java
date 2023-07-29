package com.example.six.modules.seckill.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsDTO {
    @ApiModelProperty(value = "ID",required = true)
    private Long id;

    @ApiModelProperty(value = "商品名称",required = true)
    private String goodsName;

    @ApiModelProperty(value = "商品标题",required = true)
    private String goodsTitle;

    @ApiModelProperty(value = "商品图片",required = true)
    private String goodsImg;

    @ApiModelProperty(value = "商品价格",required = true)
    private BigDecimal goodsPrice;
}
