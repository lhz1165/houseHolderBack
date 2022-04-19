package com.example.householderback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.UserInfoPageParam;

public interface IUserInfoService extends IService<UserInfo> {
    Result addUser(UserInfo userInfo);

    UserInfo getUserInfoByUserId(Integer userId);

    Page<UserInfo> pageUserInfo(UserInfoPageParam param);

    Result updateUser(UserInfo userInfo);
}
