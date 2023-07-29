package com.example.six.modules.seckill.mapper;


import com.example.six.modules.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SeckillOrderMapper {
    @Select("select * from seckill_order where user_id =#{userId} and goods_id =#{goodsId}")
    SeckillOrder getSeckillOrderByUserIdGoodsId(Integer userId,Long goodsId);

    @Insert("insert into seckill_order values(#{id},#{userId},#{orderId},#{goodsId})")
    void insert(SeckillOrder seckillOrder);

}
