package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.commom.Result;
import com.example.householderback.dao.UserInfoMapper;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.UserInfoPageParam;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserInfoServiceServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Autowired
    private AdminUserService userService;
    @Override
    public Result addUser(UserInfo userInfo) {
        //设置登录号
        List<User> list = userService.lambdaQuery().eq(User::getUsername, userInfo.getUsername()).list();
        if (list.size() > 0) {
            return Result.failed("用户名已存在");
        }
        User user = new User();
        user.setAvatar("/headImage/default.jpg");
        user.setUsername(userInfo.getUsername());
        user.setType("2");
        user.setPassword(userInfo.getPassword());
        userService.save(user);

        //设置户籍信息号
        userInfo.setStatus("2");
        userInfo.setType("2");
        userInfo.setUserId(user.getId());
        save(userInfo);

        return Result.succeed();
    }

    @Override
    public UserInfo getUserInfoByUserId(Integer userId) {
        return lambdaQuery().eq(UserInfo::getUserId,userId).one();
    }

    @Override
    public Page<UserInfo> pageUserInfo(UserInfoPageParam param) {
        return lambdaQuery().page(new Page<>(param.getCurrent(),param.getPageSize()));
    }

    @Override
    public Result updateUser(UserInfo userInfo) {
        updateById(userInfo);
        userService.lambdaUpdate().eq(User::getUsername,userInfo.getUsername()).set(User::getPassword,userInfo.getPassword()).update();
        return Result.succeed();
    }
}
