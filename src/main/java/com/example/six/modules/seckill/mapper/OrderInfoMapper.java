package com.example.six.modules.seckill.mapper;


import cn.hutool.db.sql.Order;
import com.example.six.modules.seckill.dto.SeckillOrderDTO;
import com.example.six.modules.seckill.entity.Goods;
import com.example.six.modules.seckill.entity.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderInfoMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into order_info values(#{id},#{userId},#{goodsId},#{addrId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate},#{payDate})")
    int insert(OrderInfo orderInfo);

    @Select("select * from order_info where user_id =#{userId} and goodsId =#{goodsId}")
    OrderInfo selectByUserIdGoodsId(Long userId,Long goodsId);

    @Select("SELECT t1.goods_name, t1.goods_price, t1.create_date FROM order_info AS t1 LEFT JOIN seckill_goods AS t2 ON t1.goods_id = t2.goods_id WHERE t1.id = #{orderId}")
    SeckillOrderDTO getOrderInfo(Long orderId);

    @Select("select goods_img from goods as t1 left join order_info as t2 on t1.id = t2.goods_id where t2.id =#{orderId}")
    String getImg(Long orderId);

}
