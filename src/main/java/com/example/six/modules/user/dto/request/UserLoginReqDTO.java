package com.example.six.modules.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReqDTO {

    @ApiModelProperty(value = "账号",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
