package com.example.householderback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.AdminUser;

import javax.servlet.http.HttpSession;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public interface AdminUserService extends IService<AdminUser> {
    Result<String> register(String username, String password);

    Result<String> login(String username,String password, HttpSession session);
    Result<String> logout(String username,HttpSession session);

}
