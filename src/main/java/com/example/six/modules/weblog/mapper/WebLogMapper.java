package com.example.six.modules.weblog.mapper;


import com.example.six.modules.weblog.entity.WebLogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebLogMapper {
    @Insert("insert into web_log values(#{id},#{userName},#{operation},#{httpMethod},#{httpUrl},#{classMethod},#{param},#{ip},#{createTime})")
    void insert(WebLogEntity webLogEntity);

}
