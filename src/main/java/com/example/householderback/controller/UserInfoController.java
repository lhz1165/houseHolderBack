package com.example.householderback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.UserInfoPageParam;
import com.example.householderback.entity.param.UserParam;
import com.example.householderback.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("普通用户")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * add
     */
    @ApiOperation("add")
    @PostMapping("/add")
    public Result register(@RequestBody UserInfo user) {
        return userInfoService.addUser(user);
    }


    /**
     * get
     */
    @ApiOperation("get")
    @GetMapping("/get")
    public Result<UserInfo> get(@RequestParam Integer id) {
        return Result.succeed(userInfoService.getById(id));
    }


    /**
     * update
     */
    @ApiOperation("update")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody UserInfo user) {
        userInfoService.updateUser(user);
        return Result.succeed();
    }


    /**
     * page
     */
    @ApiOperation("page")
    @PostMapping("/page")
    public Result<Page<UserInfo>> page(@RequestBody UserInfoPageParam param) {
        return Result.succeed(userInfoService.pageUserInfo(param));
    }


}
