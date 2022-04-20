package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.commom.Result;
import com.example.householderback.dao.CommentMapper;
import com.example.householderback.dao.HouseHoldMapper;
import com.example.householderback.entity.Comment;
import com.example.householderback.entity.HouseHold;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.HouseHoldMoveParam;
import com.example.householderback.entity.param.HouseHoldUpdateParam;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.entity.vo.HouseHoldVo;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.service.ICommentService;
import com.example.householderback.service.IHouseHoldService;
import com.example.householderback.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseHoldServiceImpl extends ServiceImpl<HouseHoldMapper, HouseHold> implements IHouseHoldService {

    @Autowired
    private IUserInfoService userInfoService;



    @Override
    public Page<HouseHold> page(PageParam param) {
        Page<HouseHold> page = lambdaQuery().page(new Page<>(param.getCurrent(), param.getPageSize()));
        return page;
    }

    @Override
    public void addHouseHold(HouseHold houseHold) {
        houseHold.setPeopleCount(1);
        save(houseHold);

        userInfoService.lambdaUpdate()
                .eq(UserInfo::getUsername, houseHold.getHouseholder())
                .set(UserInfo::getHouseholderId, houseHold.getId())
                .set(UserInfo::getStatus, "1")
                .update();
    }

    @Override
    public void move(HouseHoldMoveParam param) {

    }

    @Override
    public HouseHoldVo get(Integer id) {
        HouseHold entity = getById(id);
        HouseHoldVo vo = new HouseHoldVo();
        BeanUtils.copyProperties(entity, vo);
        List<UserInfo> userInfos = userInfoService.lambdaQuery().eq(UserInfo::getHouseholderId, id).list();
        vo.setUserInfos(userInfos);
        return vo;
    }

    @Override
    public Result updateHouseHold(HouseHoldUpdateParam houseHold) {
        //检查户主是否绑定过
        HouseHold one = lambdaQuery().eq(HouseHold::getHouseholder, houseHold.getHouseholder()).one();
        if (one != null && one.getId() != houseHold.getId()) {
            return Result.failed("户主已经被绑定");
        }
        //更新用户
        HouseHold en = new HouseHold();
        BeanUtils.copyProperties(houseHold, en);
        updateById(en);
        return Result.succeed();
    }


}
