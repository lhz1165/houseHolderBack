package com.example.householderback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.UserInfoPageParam;
import com.example.householderback.entity.vo.UserInfoVo;

import java.util.List;

public interface IUserInfoService extends IService<UserInfo> {
    Result addUser(UserInfo userInfo);

    UserInfo getUserInfoByUserId(Integer userId);

    Page<UserInfo> pageUserInfo(UserInfoPageParam param);

    Result updateUser(UserInfo userInfo);

    List<UserInfo> ListByMove(String moveType,Integer houseId);

    UserInfoVo get(Integer uid);

    UserInfoVo getByUserName(String username);
}
