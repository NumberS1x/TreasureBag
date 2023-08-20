package com.example.six.modules.weblog.mapper;


import com.example.six.modules.weblog.entity.WebLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebLogMapper {
    @Insert("insert into web_log values(#{userName},#{operation},#{method},#{param},#[ip],#{creatTime})")
    void insert(WebLog webLog);

}
