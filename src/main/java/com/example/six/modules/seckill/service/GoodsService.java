package com.example.six.modules.seckill.service;


import com.example.six.modules.seckill.dto.GoodsDTO;
import com.example.six.modules.seckill.dto.SeckillGoodsDTO;
import com.example.six.modules.seckill.entity.Goods;
import com.example.six.modules.seckill.entity.SeckillGoods;

import java.util.List;

public interface GoodsService {
    List<GoodsDTO> getAllGoods();

    SeckillGoodsDTO getSeckillGoods(Long id);

    List<SeckillGoods> getAllSeckillGoods();

    SeckillGoods getSeckillGoodsByGoodsId(Long goodsId);

    int reduceStock(Long goodsId);

    Goods getGoodsById(Long goodsId);
}
