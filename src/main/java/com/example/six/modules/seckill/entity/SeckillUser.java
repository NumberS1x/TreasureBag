package com.example.six.modules.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *用户实体类
 * </p>
 * @author NumberSix
 * @since 2023-07-28-15:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeckillUser implements Serializable {

    private int id;
    private String userName;
    private String phone;
    private String password;
    private String salt;
    private String head;
    private int loginCount;
    private Date registerDate;
    private Date lastLoginDate;
}
