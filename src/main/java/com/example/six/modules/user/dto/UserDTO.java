package com.example.six.modules.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *<p>
 *用户请求类
 *</p>
 *
 * @author NumeberSix
 * @since 2023-07-23-15:34
 */


@Data
public class UserDTO {
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    @ApiModelProperty(value = "真实名",required = true)
    private String realName;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

}
