package com.example.six.modules.user.service.impl;

import com.example.six.core.exception.ServiceException;
import com.example.six.core.utils.BeanMapper;
import com.example.six.core.utils.JedisUtil;
import com.example.six.core.utils.JwtUtils;
import com.example.six.core.utils.MD5Util;
import com.example.six.modules.user.dto.UserDTO;
import com.example.six.modules.user.dto.request.UserLoginReqDTO;
import com.example.six.modules.user.dto.response.UserLoginDTO;
import com.example.six.modules.user.entity.User;
import com.example.six.modules.user.mapper.UserMapper;
import com.example.six.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserDTO userDTO){
        if (userMapper.queryUser(userDTO.getUserName())>0){
            throw new ServiceException(1,"用户已经存在，请更换用户名！");
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setRealName(userDTO.getRealName());
        user.setPassword(MD5Util.MD5(userDTO.getPassword()));
        userMapper.addUser(user);
    }

    @Override
    public UserLoginDTO login(UserLoginReqDTO reqDTO){
        String username = reqDTO.getUserName();
        if (userMapper.getUserByName(username) == null){
            throw new ServiceException(1,"该用户不存在");
        }
        String password = MD5Util.MD5(reqDTO.getPassword());
        User user = userMapper.login(username,password);
        if (user == null){
            throw new ServiceException(1  ,"用户名或者密码错误");
        }
        return this.setToken(user);

    }

    @Override
    public User getUser(String userName){
        return userMapper.getUserByName(userName);
    }


    /**
     * 保存会话信息
     * @param user
     * @return
     */
    private UserLoginDTO setToken(User user){
        UserLoginDTO reqDTO = new UserLoginDTO();
        BeanMapper.copy(user,reqDTO);
        //生成token
        String token = JwtUtils.sign(user.getUserName());
        reqDTO.setToken(token);
        JedisUtil.setToken(token);
        return reqDTO;
    }

}
