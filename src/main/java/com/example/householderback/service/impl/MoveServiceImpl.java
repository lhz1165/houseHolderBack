package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.commom.Result;
import com.example.householderback.dao.MoveMapper;
import com.example.householderback.entity.Move;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.service.IMoveService;
import com.example.householderback.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class MoveServiceImpl  extends ServiceImpl<MoveMapper, Move> implements IMoveService {
    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public Result<Void> updateMove(Move param) {
        if (param.getUsernames() == null||param.getUsernames().isEmpty()) {
            return Result.failed("用户不能为空");
        }

        if (param.getType()=="1"&&param.getHouseHoldId() == null) {
            return Result.failed("户籍不能为空");
        }

        for (String username : param.getUsernames()) {
            Move move = new Move();
            BeanUtils.copyProperties(param,move);
            move.setCreateTime(LocalDateTime.now());
            move.setStatus("1");
            move.setUsername(username);
            save(move);

            //变成迁入
            if (Objects.equals(param.getType(), "1")) {
                //更新用户迁入迁出状态
                userInfoService.lambdaUpdate()
                        .eq(UserInfo::getUsername, username)
                        .set(UserInfo::getStatus, param.getType())
                        .set(UserInfo::getHouseholderId, param.getHouseHoldId())
                        .update();
                //牵出
            }else {
                userInfoService.lambdaUpdate()
                        .eq(UserInfo::getUsername, username)
                        .set(UserInfo::getStatus, param.getType())
                        .set(UserInfo::getHouseholderId, null)
                        .update();
            }
        }

        return Result.succeed();
    }

    @Override
    public Result<Void> pay(Integer id, Float payment) {
        Move move = lambdaQuery().eq(Move::getId, id).one();
        if (Objects.equals(move.getStatus(), "2")) {
            return Result.failed("已经迁移成功");
        }

        if (payment != null) {
            if (move.getPayment() != null && !Objects.equals(move.getPayment(), payment)) {
                return Result.failed("金额有误");
            }
        }
        lambdaUpdate().eq(Move::getId,id).set(Move::getStatus,"2").update();
        return Result.succeed();

    }
}
