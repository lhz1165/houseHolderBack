package com.example.householderback.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.HouseHold;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.entity.param.UpdateUserParam;
import com.example.householderback.entity.param.UserParam;
import com.example.householderback.entity.vo.LoginUserVo;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.service.IHouseHoldService;
import com.example.householderback.service.IUserInfoService;
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

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IUserInfoService userInfoService;
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

    @ApiOperation("page")
    @PostMapping("/user/page")
    public Result<Page<UserInfo>> page(@RequestBody PageParam param) {
        //查询所有迁出并且缴费的用户
        Page<UserInfo> page = userInfoService
                .lambdaQuery()
                .eq(UserInfo::getStatus, "2")
                .eq(UserInfo::getPaid,true)
                .page(new Page<>(param.getCurrent(), param.getPageSize()));
        return Result.succeed(page);
    }
}
