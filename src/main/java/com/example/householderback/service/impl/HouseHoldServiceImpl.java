package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.dao.CommentMapper;
import com.example.householderback.dao.HouseHoldMapper;
import com.example.householderback.entity.Comment;
import com.example.householderback.entity.HouseHold;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.HouseHoldMoveParam;
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
        save(houseHold);
    }

    @Override
    public void move(HouseHoldMoveParam param) {

    }

    @Override
    public HouseHoldVo get(Integer id) {
        HouseHold entity = getById(id);
        HouseHoldVo vo = new HouseHoldVo();
        BeanUtils.copyProperties(entity,vo);
        List<UserInfo> userInfos = userInfoService.lambdaQuery().eq(UserInfo::getHouseholderId, id).list();
        vo.setUserInfos(userInfos);
        return vo;
    }

    @Override
    public void updateHouseHold(HouseHold houseHold) {
        updateById(houseHold);
    }
}
