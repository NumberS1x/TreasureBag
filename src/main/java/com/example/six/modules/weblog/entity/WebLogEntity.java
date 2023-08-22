package com.example.six.modules.weblog.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebLogEntity implements Serializable {

    private Integer id;
    private String userName;
    private String operation;
    private String httpMethod;
    private String httpUrl;
    private String classMethod;
    private String param;
    private String ip;
    private Date createTime;
}
