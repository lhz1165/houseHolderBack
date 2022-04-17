package com.example.householderback.controller;


import com.example.householderback.commom.Result;
import com.example.householderback.entity.User;
import com.example.householderback.entity.param.UpdateUserParam;
import com.example.householderback.entity.param.UserParam;
import com.example.householderback.entity.vo.LoginUserVo;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.utils.LoginUser;
import com.example.householderback.utils.SpringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@CrossOrigin
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
        return userService.register(user.getUsername(), user.getPassword());
    }

    /**
     * 登录
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserParam user) {
        return userService.login(user.getUsername(),user.getPassword(),user.getType(),SpringUtils.getHttpSession());
    }

    @ApiOperation("哈喽")
    @PostMapping("/hello")
    public Result<String> hello(@LoginUser User user) {
        if (user == null) {
            return Result.succeed(Result.RetCode.UNAUTHORIZED.getCode() + "");
        }
        return Result.succeed("hello" + user.getUsername());
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result<String> logout(@LoginUser User user) {
        if (user == null) {
            return Result.succeed(Result.RetCode.UNAUTHORIZED.getCode() + "");
        }
        return userService.logout(user.getUsername(), SpringUtils.getHttpSession());
    }

    @ApiOperation("当前用户")
    @PostMapping("/getUserInfo")
    public Result<LoginUserVo> getUserInfo(@LoginUser User user) {
        if (user == null) {
            return Result.succeed(null);
        }
        LoginUserVo vo = new LoginUserVo();
        BeanUtils.copyProperties(user,vo);
        return Result.succeed(vo);
    }

    @ApiOperation("update用户")
    @PostMapping("/user/update")
    public Result<LoginUserVo> update(@RequestBody UpdateUserParam param) {
        userService.upDateAvatar(param);
        return Result.succeed();
    }

    @ApiOperation("update用户")
    @PostMapping("/user/updatePass")
    public Result<LoginUserVo> updatePass(@RequestBody UpdateUserParam param) {
        userService.updatePass(param);
        return Result.succeed();
    }


}
