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
public class WebLog implements Serializable {

    private Integer id;
    private String userName;
    private String operation;
    private String method;
    private String param;
    private String ip;
    private Date createTime;
}
