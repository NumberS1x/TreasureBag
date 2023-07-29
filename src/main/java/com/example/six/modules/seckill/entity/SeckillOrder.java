package com.example.six.modules.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *秒杀订单信息实体类
 * </p>
 * @author NumberSix
 * @since 2023-07-28-15:02
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeckillOrder implements Serializable {

    private Long id;

    private Long userId;

    private Long orderId;

    private Long goodsId;
}
