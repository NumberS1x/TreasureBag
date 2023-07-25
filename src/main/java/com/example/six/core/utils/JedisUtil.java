package com.example.six.core.utils;


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
        jedis.set(username,token,new SetParams().px(6000L));
    }

    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static boolean verifyToken(String token){
        String username = JwtUtils.getUsername(token);
        if (jedis.get(username) == null){
            throw new ServiceException(401,"验证码已经过期！");
        }
        System.out.println(jedis.get(token));
        return true;
    }
}
