package com.example.six.modules.user.controller;


import com.example.six.core.annotion.UserLoginToken;
import com.example.six.core.annotion.WebLog;
import com.example.six.core.api.controller.BaseController;
import com.example.six.core.utils.ApiRest;
import com.example.six.modules.user.dto.UserDTO;
import com.example.six.modules.user.dto.request.UserLoginReqDTO;
import com.example.six.modules.user.dto.response.UserLoginDTO;
import com.example.six.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiRest save(@RequestBody UserDTO reqDto){
        userService.save(reqDto);
        return super.success();
    }

    @WebLog("用户登录")
    @PostMapping("/login")
    public ApiRest login(@RequestBody UserLoginReqDTO reqDTO){
        UserLoginDTO respDTO = userService.login(reqDTO);
        return super.success(respDTO);
    }

    @UserLoginToken
    @RequestMapping(value = "/getMessage",method = RequestMethod.GET)
    public  String getMessage(){
        return "验证成功！";
    }



}
