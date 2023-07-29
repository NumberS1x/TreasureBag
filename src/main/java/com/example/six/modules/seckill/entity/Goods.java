package com.example.six.modules.seckill.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *商品实体类
 * </p>
 * @author NumberSix
 * @since 2023-07-28-13:54
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    private Long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private BigDecimal goodsPrice;

    private Integer goodsStock;

    private Date createDate;

    private Date updateDate;

    private String goodsDetail;
}
