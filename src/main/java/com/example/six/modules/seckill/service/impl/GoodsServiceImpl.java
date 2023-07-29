package com.example.six.modules.seckill.service.impl;


import com.example.six.core.exception.ServiceException;
import com.example.six.core.utils.BeanMapper;
import com.example.six.modules.seckill.dto.GoodsDTO;
import com.example.six.modules.seckill.dto.SeckillGoodsDTO;
import com.example.six.modules.seckill.entity.Goods;
import com.example.six.modules.seckill.entity.SeckillGoods;
import com.example.six.modules.seckill.mapper.GoodsMapper;
import com.example.six.modules.seckill.mapper.SeckillGoodsMapper;
import com.example.six.modules.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;


//    获取秒杀商品的列表
    @Override
    public List<GoodsDTO> getAllGoods(){
        List<GoodsDTO> goodsDTOList = new ArrayList<GoodsDTO>();
        List<Goods> goodsList = goodsMapper.getAllGoods();
        for(Goods i: goodsList){
            GoodsDTO goodsDTO = new GoodsDTO();
            BeanMapper.copy(i,goodsDTO);
            goodsDTOList.add(goodsDTO);
        }
        return goodsDTOList;
    }

//    获取想要秒杀商品的详细信息(前端)
    @Override
    public SeckillGoodsDTO getSeckillGoods(Long id){
        SeckillGoodsDTO seckillGoods = goodsMapper.getSeckillGoods(id);
        if (seckillGoods == null){
            throw new ServiceException(1,"商品不存在");
        }
        return seckillGoods;
    }

//    获取秒杀商品的信息
    @Override
    public SeckillGoods getSeckillGoodsByGoodsId(Long goodsId){
        SeckillGoods seckillGoods= seckillGoodsMapper.getSeckillGoodsByGoodsId(goodsId);
        if (seckillGoods == null){
            throw new ServiceException(1,"商品不存在");
        }
        return seckillGoods;
    }

//    获取所有秒杀商品的信息
    @Override
    public List<SeckillGoods> getAllSeckillGoods(){
        return seckillGoodsMapper.getAllSeckillGoods();
    }

//    获取商品信息
    @Override
    public Goods getGoodsById(Long goodsId){
        return goodsMapper.getById(goodsId);
    }
//    减少秒杀库存
    @Override
    public int reduceStock(Long goodsId){
        return seckillGoodsMapper.update(goodsId);
    }
}

