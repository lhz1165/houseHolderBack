package com.example.householderback.controller;

import com.example.householderback.commom.Result;
import com.example.householderback.entity.UserInfo;
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
    public Result<Void> register(@RequestBody UserInfo user) {
        userInfoService.addUser(user);
        return Result.succeed();
    }


    /**
     * add
     */
    @ApiOperation("get")
    @PostMapping("/get")
    public Result<UserInfo> get(@RequestParam Integer id) {
        return Result.succeed(userInfoService.getById(id));
    }


    /**
     * update
     */
    @ApiOperation("update")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody UserInfo user) {
        userInfoService.updateById(user);
        return Result.succeed();
    }


}
