package com.example.six.modules.seckill.controller;


import com.example.six.core.api.controller.BaseController;
import com.example.six.core.mq.MQSender;
import com.example.six.core.mq.SeckillMessage;
import com.example.six.core.utils.ApiRest;
import com.example.six.core.utils.JedisUtil;
import com.example.six.core.utils.MD5Util;
import com.example.six.modules.seckill.dto.SeckillGoodsDTO;
import com.example.six.modules.seckill.entity.SeckillGoods;
import com.example.six.modules.seckill.entity.SeckillOrder;
import com.example.six.modules.seckill.service.GoodsService;
import com.example.six.modules.seckill.service.SeckillOrderService;
import com.example.six.modules.user.entity.User;
import com.example.six.modules.user.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger.schema.ApiModelTypeNameProvider;

import java.util.List;

@RestController
@RequestMapping("/seckill")
public class SeckillController extends BaseController implements InitializingBean {

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    MQSender mqSender;


    /**
     * 将秒杀商品的信息初始化在redis缓存
     */

    public void afterPropertiesSet() throws Exception {
        List<SeckillGoods> goodsList = goodsService.getAllSeckillGoods();
        if (goodsList == null) {
            return;
        }
        for (SeckillGoods goods : goodsList) {
            JedisUtil.setSeckillStock(goods.getGoodsId(),goods.getStockCount());
        }
    }





//获取秒杀路径
    @GetMapping("/path")
    public ApiRest getSeckillPath(@RequestParam("userId") Long userId,
                                  @RequestParam("goodsId") Long goodsId){
        String path = seckillOrderService.createPath(userId,goodsId);
        return super.success(path);
    }
//执行秒杀
    @PostMapping("/{path}/seckill")
    public ApiRest doSeckill(@PathVariable("path") String path,
                             @RequestParam("goodsId") Long goodsId,
                             @RequestParam("userId") Long userId){

        //验证path
        boolean check = seckillOrderService.verifyPath(userId,goodsId,path);
        if (!check){
            return super.failure("路径不存在！");
        }
        Integer id =Integer.valueOf(String.valueOf(userId));
        //判断是否已经秒杀到了，防止重复秒杀
        SeckillOrder order =seckillOrderService.getSeckillOrderByUserIdGoodsId(id,goodsId);
        if (order!=null){
            return super.failure("重复秒杀");
        }
        //预减库存
        Long stock = JedisUtil.getStock(goodsId);
        if (stock < 0){
            return super.failure("秒杀结束");
        }

        //入队
        User user = userService.getById(id);
        SeckillMessage mm = new SeckillMessage();
        mm.setUser(user);
        mm.setGoodsId(goodsId);
        mqSender.sendSeckillMessage(mm);
        return super.success();
    }

//获取秒杀结果
    @GetMapping("/result")
    public ApiRest getResult(@RequestParam("goodsId") Long goodsId,
                             @RequestParam("userId") Long userId){
        Integer id = Integer.valueOf(String.valueOf(userId));
        Long result = seckillOrderService.getSckillResult(id,goodsId);
        return super.success(result);
    }

}
