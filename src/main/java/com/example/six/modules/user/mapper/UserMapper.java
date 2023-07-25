package com.example.six.modules.user.mapper;


import com.example.six.modules.user.dto.response.UserLoginDTO;
import com.example.six.modules.user.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into tb_user values(#{id},#{userName},#{realName},#{password})")
    void addUser(User user);

    @Select("select count(*) from tb_user where username=#{userName}")
    Integer queryUser(String userName);

    @Select("select * from tb_user where id=#{id}")
    User queryUserById(Integer id);

    @Select("select * from tb_user where username=#{userName}")
    User getUserByName(String userName);

    @Select("select * from tb_user where username=#{userName} and password =#{password}")
    User login(String userName,String password);

}
