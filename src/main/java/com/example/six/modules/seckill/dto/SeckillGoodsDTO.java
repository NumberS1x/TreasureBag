package com.example.six.modules.seckill.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillGoodsDTO {
    @ApiModelProperty(value = "ID",required = true)
    private Long id;

    @ApiModelProperty(value = "商品名称",required = true)
    private String goodsName;

    @ApiModelProperty(value = "商品标题",required = true)
    private String goodsTitle;

    @ApiModelProperty(value = "商品图片",required = true)
    private String goodsImg;

    @ApiModelProperty(value = "商品详细",required = true)
    private String goodsDetail;

    @ApiModelProperty(value = "商品价格",required = true)
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "秒杀开始时间",required = true)
    private Date startDate;

    @ApiModelProperty(value = "秒杀结束时间",required = true)
    private Date endDate;

    @ApiModelProperty(value = "商品库存",required = true)
    private Integer goodsStock;

    @ApiModelProperty(value = "创建时间",required = true)
    private Date createDate;

    @ApiModelProperty(value = "更新时间",required = true)
    private Date updateDate;
}
