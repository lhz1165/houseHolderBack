package com.example.householderback.controller;


import com.example.householderback.commom.Result;
import com.example.householderback.entity.AdminUser;
import com.example.householderback.entity.param.UserParam;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.utils.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@RestController
@Api("用户接口")
public class UserController {
    @Autowired
    private AdminUserService userService;

    /**
     * 注册登录
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserParam user) {
        return userService.register(user.getUsername(),user.getPassword());
    }
    /**
     * 登录
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserParam user) {
        return userService.login(user.getUsername(),user.getPassword());
    }
    @ApiOperation("哈喽")
    @PostMapping("/hello")
    public Result<String> hello(@LoginUser AdminUser user) {
        return Result.succeed("hello"+user.getUsername());
    }


}
