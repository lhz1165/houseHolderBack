package com.example.householderback.service.impl;


import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.commom.Result;
import com.example.householderback.dao.UserMapper;
import com.example.householderback.entity.User;
import com.example.householderback.entity.param.UpdateUserParam;
import com.example.householderback.exception.MyException;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.utils.jwt.JwtUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@Service
public class AdminUserServiceImpl extends ServiceImpl<UserMapper, User> implements AdminUserService {

    @Override
    public Result<String> register(String username, String password) {
        User userInDB = lambdaQuery()
                .eq(User::getUsername, username)
                .one();
        if (userInDB != null) {
            return Result.failed("用户名存在");
        }
        User user = new User(username, password);
        save(user);
        return Result.succeed("注册成功");
    }

    @Override
    public Result<String> login(String username, String password, String type, HttpSession session) {
        User userInDB = lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getType, type)
                .one();
        if (userInDB == null) {
            return Result.failed("用户名不存在");
        }
        if (!userInDB.getPassword()
                .equals(password)) {
            return Result.failed("密码错误");
        }
        String token = JwtUtil.createToken(userInDB);
        session.setAttribute(username, token);
        return Result.succeed(token);

    }

    @Override
    public Result<String> logout(String username, HttpSession session) {
        session.removeAttribute(username);
        return Result.succeed();
    }

    @Override
    public Result<Boolean> upDateAvatar(UpdateUserParam param) {

        return Result.succeed(lambdaUpdate().eq(User::getId,param.getId()).set(User::getAvatar,param.getPicAddr()).update());
    }

    @Override
    public void updatePass(UpdateUserParam param) {
        User user = lambdaQuery().eq(User::getId, param.getId()).one();
        if (!user.getPassword().equals(param.getOldPass())) {
            throw new MyException("原密码输入错误");
        }

        if (!param.getPassword2().equals(param.getPassword())) {
            throw new MyException("密码不一致");
        }
        lambdaUpdate().eq(User::getId,param.getId()).set(User::getPassword,param.getPassword()).update();

    }


}
