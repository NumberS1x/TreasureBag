package com.example.six.modules.user.service;


import com.example.six.modules.user.dto.UserDTO;
import com.example.six.modules.user.dto.request.UserLoginReqDTO;
import com.example.six.modules.user.dto.response.UserLoginDTO;
import com.example.six.modules.user.entity.User;

public interface UserService {
    void save(UserDTO userDTO);
    UserLoginDTO login(UserLoginReqDTO reqDTO);
    User getUser(String userName);
    User getById(Integer id);
}
