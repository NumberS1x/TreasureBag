package com.example.six.modules.seckill.service;

import com.example.six.modules.seckill.entity.OrderInfo;
import com.example.six.modules.seckill.entity.SeckillGoods;
import com.example.six.modules.seckill.entity.SeckillOrder;
import com.example.six.modules.user.entity.User;
import io.swagger.models.auth.In;

public interface SeckillOrderService {
    SeckillOrder getSeckillOrderByUserIdGoodsId(Integer userId,Long goodsId);
    OrderInfo insert(User user, SeckillGoods goods);
    String createPath(Long userId,Long goodsId);
    boolean verifyPath(Long userId,Long goodsId,String path);
}
