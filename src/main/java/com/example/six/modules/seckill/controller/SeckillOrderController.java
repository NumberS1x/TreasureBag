package com.example.six.modules.seckill.controller;


import com.example.six.core.annotion.UserLoginToken;
import com.example.six.core.api.controller.BaseController;
import com.example.six.core.utils.ApiRest;
import com.example.six.modules.seckill.dto.SeckillOrderDTO;
import com.example.six.modules.seckill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@UserLoginToken
@RestController
@RequestMapping("/order")
public class SeckillOrderController extends BaseController {
    @Autowired
    private SeckillOrderService seckillOrderService;


    @UserLoginToken
    @GetMapping("/detail")
    public ApiRest orderInfo(@RequestParam("orderId") Long orderId){
        SeckillOrderDTO seckillOrderDTO = seckillOrderService.getOrderInfo(orderId);
        return super.success(seckillOrderDTO);

    }
}
