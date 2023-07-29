package com.example.six.modules.seckill.service.impl;

import com.example.six.core.exception.ServiceException;
import com.example.six.core.utils.JedisUtil;
import com.example.six.core.utils.MD5Util;
import com.example.six.modules.seckill.entity.Goods;
import com.example.six.modules.seckill.entity.OrderInfo;
import com.example.six.modules.seckill.entity.SeckillGoods;
import com.example.six.modules.seckill.entity.SeckillOrder;
import com.example.six.modules.seckill.mapper.OrderInfoMapper;
import com.example.six.modules.seckill.mapper.SeckillOrderMapper;
import com.example.six.modules.seckill.service.GoodsService;
import com.example.six.modules.seckill.service.SeckillOrderService;
import com.example.six.modules.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service("seckillOrderService")
public class SeckillOrderServiceImpl implements SeckillOrderService {
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private GoodsService goodsService;

    @Override
    public SeckillOrder getSeckillOrderByUserIdGoodsId(Integer userId, Long goodsId){
        SeckillOrder seckillOrder = seckillOrderMapper.getSeckillOrderByUserIdGoodsId(userId, goodsId);
        return seckillOrder;
    }

    @Override
    public OrderInfo insert(User user, SeckillGoods goods){
        int success = goodsService.reduceStock(goods.getGoodsId());
        if (success == 1){
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setCreateDate(new Date());
            orderInfo.setAddrId(0L);
            orderInfo.setGoodsCount(1);
            orderInfo.setGoodsId(goods.getId());
            orderInfo.setGoodsName(goodsService.getGoodsById(goods.getGoodsId()).getGoodsName());
            orderInfo.setGoodsPrice(goods.getSeckillPrice());
            orderInfo.setOrderChannel(1);
            orderInfo.setStatus(0);
            orderInfo.setUserId((long) user.getId());
            //添加信息进订单
            long orderId = orderInfoMapper.insert(orderInfo);
            log.info("orderId -->" + orderId + "");
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setGoodsId(goods.getId());
            seckillOrder.setOrderId(orderInfo.getId());
            seckillOrder.setUserId((long) user.getId());
            //插入秒杀表
            seckillOrderMapper.insert(seckillOrder);
            return orderInfo;
        }else{
            throw new ServiceException(1,"秒杀失败");
        }
    }

    @Override
    public String createPath(Long userId,Long goodsId){
        return JedisUtil.setPath(userId,goodsId);
    }

    @Override
    public boolean verifyPath(Long userId,Long goodsId,String path){
        return JedisUtil.verifyPath(path,userId,goodsId);
    }
}
