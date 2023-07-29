package com.example.six.modules.seckill.mapper;


import com.example.six.modules.seckill.entity.SeckillGoods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SeckillGoodsMapper {
    @Select("select * from seckill_goods where goods_id =#{goodsId}")
    SeckillGoods getSeckillGoodsByGoodsId(Long goodsID);

    @Select("select * from seckill_goods")
    List<SeckillGoods> getAllSeckillGoods();

    @Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    int update(Long goodsId);

}
