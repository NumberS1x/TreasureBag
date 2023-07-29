package com.example.six.modules.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 *商品订单信息实体类
 * </p>
 * @author NumberSix
 * @since 2023-07-28-13:56
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo implements Serializable {
    private Long id;

    private Long userId;

    private Long goodsId;

    private Long addrId;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    private Integer orderChannel;

    private Integer status;

    private Date createDate;

    private Date payDate;

}
