package com.example.householderback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.UserInfoPageParam;
import com.example.householderback.entity.param.UserParam;
import com.example.householderback.entity.vo.UserInfoVo;
import com.example.householderback.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<UserInfoVo> get(@RequestParam Integer id) {
        return Result.succeed(userInfoService.get(id));
    }

    /**
     * get
     */
    @ApiOperation("getByUserName")
    @GetMapping("/getByUserName")
    public Result<UserInfoVo> getByUserName(@RequestParam String username) {
        return Result.succeed(userInfoService.getByUserName(username));
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
     * 注销或者恢复
     */
    @ApiOperation("update")
    @PostMapping("/deleteOrRecover")
    public Result<Void> deleteOrRecover(@RequestBody UserInfo user) {
        userInfoService.deleteOrRecover(user);
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

    /**
     * 获取迁入或者迁出的用户列表
     * @param moveType
     * @return
     */
    @ApiOperation("list")
    @GetMapping("/list")
    public Result<List<UserInfo>> getUserList(@RequestParam String moveType,@RequestParam Integer houseHoldId) {
        return Result.succeed(userInfoService.ListByMove(moveType,houseHoldId));
    }


}
