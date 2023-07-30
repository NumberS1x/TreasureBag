package com.example.six.core.utils;


import com.alibaba.fastjson.JSON;
import com.example.six.core.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;


public class JedisUtil {
    private static final Jedis jedis = new Jedis("localhost",6379);

    /**
     * 设置token在redis
     */
    public static void setToken(String token){
        String username = JwtUtils.getUsername(token);
        jedis.set(username,token,new SetParams().ex(60 * 60));
    }

    /**
     * 设置秒杀路径
     */
    public static String setPath(Long userId,Long goodsId){
        String path = MD5Util.MD5(userId.toString() + goodsId.toString());
        jedis.set(userId.toString() + goodsId.toString(),path,new SetParams().ex(15));
        return path;
    }

    /**
     * 验证秒杀路径是否存在
     */
    public static boolean verifyPath(String path,Long userId,Long goodsId){
        String pathKey = userId.toString() + goodsId.toString();
        String res = jedis.get(pathKey);
        if (res == null || !res.equals(path)){
            throw new ServiceException(1,"错误的秒杀路径");
        }
        return true;
    }

    /**
     * 将商品初始化在redis中
     */
    public static void setSeckillStock(Long goodsId,Integer stockCount){
        jedis.set(goodsId.toString(),stockCount.toString(),new SetParams().ex(10000));
    }

    /**
     * 获取初始话的商品的
     */
    public static Long getStock(Long goodsId){
        Long stock = Long.parseLong(jedis.get(goodsId.toString())) - 1;
        jedis.set(goodsId.toString(),stock.toString(),new SetParams().ex(10000));
        return stock;
    }

    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static boolean verifyToken(String token){
        String username = JwtUtils.getUsername(token);
        if (jedis.get(username) == null){
            return false;
        }
        System.out.println(jedis.get(token));
        return true;
    }
    /**
     * bean 转 String
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * string转bean
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 判断秒杀商品是否还存在
     */
    public static boolean goodsExist(Long goodsId){
        return jedis.exists(goodsId.toString());
    }


}
