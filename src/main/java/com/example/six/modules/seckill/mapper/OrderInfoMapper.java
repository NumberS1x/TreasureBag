package com.example.six.modules.seckill.mapper;


import cn.hutool.db.sql.Order;
import com.example.six.modules.seckill.entity.Goods;
import com.example.six.modules.seckill.entity.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderInfoMapper {
    @Insert("insert into order_info values(#{id},#{userId},#{goodsId},#{addrId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate},#{payDate})")
    int insert(OrderInfo orderInfo);

    @Select("select * from order_info where user_id =#{userId} and goodsId =#{goodsId}")
    OrderInfo selectByUserIdGoodsId(Long userId,Long goodsId);

}
