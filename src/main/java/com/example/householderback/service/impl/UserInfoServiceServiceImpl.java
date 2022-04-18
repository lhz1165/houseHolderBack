package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.dao.UserInfoMapper;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Autowired
    private AdminUserService userService;
    @Override
    public void addUser(UserInfo userInfo) {
        save(userInfo);
        User user = new User();
        user.setAvatar("/headImage/default.jpg");
        user.setUsername(userInfo.getUsername());
        user.setType("2");
        user.setPassword("123456");
        userService.save(user);
    }
}
