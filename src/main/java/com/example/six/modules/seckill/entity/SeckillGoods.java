package com.example.six.modules.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *秒杀商品实体类
 * </p>
 * @author NumberSix
 * @since 2023-07-28-15:01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillGoods implements Serializable {
    private Long id;

    private Long goodsId;

    private BigDecimal seckilPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;
}
