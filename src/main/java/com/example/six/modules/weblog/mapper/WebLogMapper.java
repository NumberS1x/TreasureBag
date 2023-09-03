package com.example.six.modules.weblog.mapper;


import com.example.six.modules.weblog.entity.WebLogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WebLogMapper {
    @Insert("insert into web_log values(#{id},#{userName},#{operation},#{httpMethod},#{httpUrl},#{classMethod},#{param},#{ip},#{createTime})")
    void insert(WebLogEntity webLogEntity);

    @Select("select * from web_log")
    List<WebLogEntity> getLogList();
}
