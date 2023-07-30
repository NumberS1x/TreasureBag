package com.example.six.core.mq;

import com.example.six.core.utils.JedisUtil;
import com.example.six.modules.seckill.entity.SeckillGoods;
import com.example.six.modules.seckill.entity.SeckillOrder;
import com.example.six.modules.seckill.service.GoodsService;
import com.example.six.modules.seckill.service.SeckillOrderService;
import com.example.six.modules.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MQReceiver {

    private static final Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    GoodsService goodsService;



    @Autowired
    SeckillOrderService seckillOrderService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        // todo 如果这里出现异常可以进行补偿，重试，重新执行此逻辑，如果超过一定次数还是失败可以将此秒杀置为无效，恢复redis库存
        log.info("receive message:" + message);
        SeckillMessage mm = JedisUtil.stringToBean(message, SeckillMessage.class);
        User user = mm.getUser();
        long goodsId = mm.getGoodsId();
//               获取秒杀商品的详情信息
        SeckillGoods seckillGoods = goodsService.getSeckillGoodsByGoodsId(goodsId);
        int stock = seckillGoods.getStockCount();
        log.info(seckillGoods.toString());
        if (stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        SeckillOrder order = seckillOrderService.getSeckillOrderByUserIdGoodsId(user.getId(),goodsId);
        if (order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        seckillOrderService.insert(user, seckillGoods);
    }
}
