package com.example.six.modules.seckill.mapper;

import com.example.six.modules.seckill.dto.SeckillGoodsDTO;
import com.example.six.modules.seckill.entity.Goods;
import com.example.six.modules.seckill.entity.SeckillGoods;
import com.example.six.modules.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods")
    List<Goods> getAllGoods();

    @Select("SELECT t1.*, t2.stock_count, t2.start_date, t2.end_date, t2.seckil_price FROM goods AS t1 LEFT JOIN seckill_goods AS t2 ON t1.id = t2.goods_id WHERE t1.id = #{id}")
    SeckillGoodsDTO getSeckillGoods(Long id);

    @Select("SELECT t1.*, t2.stock_count, t2.start_date, t2.end_date FROM goods AS t1 LEFT JOIN seckill_goods AS t2 ON t1.id = t2.goods_id")
    List<SeckillGoods> getAllSeckillGoods();

    @Select("select * from goods where id =#{goodsId}")
    Goods getById(Long goodsId);
}
