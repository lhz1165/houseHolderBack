package com.example.householderback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {
    void addUser(UserInfo userInfo);
}
