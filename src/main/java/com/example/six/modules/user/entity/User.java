package com.example.six.modules.user.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 *用户实体类
 * </p>
 * @author NumberSix
 * @since 2023-07-23-15:30
 */

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String realName;
    private String password;

}
