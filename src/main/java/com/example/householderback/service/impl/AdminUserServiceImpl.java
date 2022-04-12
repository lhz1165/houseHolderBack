package com.example.householderback.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.commom.Result;
import com.example.householderback.dao.UserMapper;
import com.example.householderback.entity.AdminUser;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.utils.jwt.JwtUtil;
import org.springframework.stereotype.Service;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@Service
public class AdminUserServiceImpl extends ServiceImpl<UserMapper, AdminUser> implements AdminUserService {

    @Override
    public Result<String> register(String username, String password) {
        AdminUser userInDB = lambdaQuery()
                .eq(AdminUser::getUsername, username)
                .one();
        if (userInDB != null) {
            return Result.failed("用户名存在");
        }
        AdminUser user = new AdminUser(username,password);
        save(user);
        return Result.succeed("注册成功");
    }

    @Override
    public Result<String> login(String username,String password) {
        AdminUser userInDB = lambdaQuery()
                .eq(AdminUser::getUsername,username)
                .one();
        if (userInDB == null) {
            return Result.failed("用户名不存在");
        }
        if (!userInDB.getPassword()
                .equals(password)) {
            return Result.failed("密码错误");
        }
        return Result.succeed(JwtUtil.createToken(userInDB));

    }







}
