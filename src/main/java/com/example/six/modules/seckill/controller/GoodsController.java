package com.example.six.modules.seckill.controller;


import com.example.six.core.annotion.UserLoginToken;
import com.example.six.core.annotion.WebLog;
import com.example.six.core.api.controller.BaseController;
import com.example.six.core.utils.ApiRest;
import com.example.six.modules.seckill.dto.GoodsDTO;
import com.example.six.modules.seckill.dto.SeckillGoodsDTO;
import com.example.six.modules.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    @UserLoginToken
    @GetMapping("/list")
    ApiRest goodsList(){
        List<GoodsDTO> goodsDTOList = goodsService.getAllGoods();
        return super.success(goodsDTOList);
    }

    @WebLog("获取商品详情")
    @UserLoginToken
    @GetMapping("/detail/{id}")
    ApiRest goodsDetail(@PathVariable("id") Long id){
        SeckillGoodsDTO seckillGoods = goodsService.getSeckillGoods(id);
        return success(seckillGoods);
    }

}
