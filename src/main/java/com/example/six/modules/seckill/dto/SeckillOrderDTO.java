package com.example.six.modules.seckill.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillOrderDTO {

    @ApiModelProperty(value = "商品名称",required = true)
    private String goodsName;

    @ApiModelProperty(value = "订单价格",required = true)
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品图片",required = true)
    private String goodsImg;

    @ApiModelProperty(value = "订单创建时间",required = true)
    private Date createDate;
}
