package com.example.six.core.exception;


import com.example.six.core.utils.ApiRest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException{
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 从结果初始化
     * @param apiRest
     */
    public ServiceException(ApiRest apiRest){
        this.code = apiRest.getCode();
        this.msg = apiRest.getMsg();
    }

}
