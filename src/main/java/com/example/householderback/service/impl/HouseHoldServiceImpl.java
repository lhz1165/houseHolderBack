package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
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
import com.example.householderback.exception.MyException;
import com.example.householderback.service.*;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HouseHoldServiceImpl extends ServiceImpl<HouseHoldMapper, HouseHold> implements IHouseHoldService {

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IMoveService moveService;


    @Override
    public Page<HouseHold> page(PageParam param) {
        Page<HouseHold> page = lambdaQuery().page(new Page<>(param.getCurrent(), param.getPageSize()));
        return page;
    }

    @Override
    public void addHouseHold(HouseHold houseHold) {
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), UserInfo.class);

        if (!StringUtils.hasLength(houseHold.getHouseholder())) {
            throw new MyException("请选择户主");
        }

        houseHold.setPeopleCount(1);
        save(houseHold);

        //把户主变成迁入状态
        UserInfo userinfo = userInfoService.lambdaQuery().eq(UserInfo::getUsername, houseHold.getHouseholder()).one();
        userinfo.setPaid(true);
        userinfo.setHouseholderId(houseHold.getId());
        userinfo.setStatus("1");
        userInfoService.updateById(userinfo);
    }

    @Override
    public void move(HouseHoldMoveParam param) {

    }


    @Override
    public HouseHoldVo get(Integer id) {
        HouseHold entity = getById(id);
        HouseHoldVo vo = new HouseHoldVo();
        BeanUtils.copyProperties(entity, vo);
        List<UserInfo> userInfos = userInfoService
                .lambdaQuery()
                .eq(UserInfo::getHouseholderId, id)
                .eq(UserInfo::getPaid, true)
                .list();

        vo.setUserInfos(userInfos);
        return vo;
    }

    @Override
    public Result updateHouseHold(HouseHoldUpdateParam houseHold) {
        if (!StringUtils.hasLength(houseHold.getHouseholder())) {
            return Result.failed("请选择户主");
        }

        HouseHold oldHouseHold = lambdaQuery().eq(HouseHold::getId, houseHold.getId()).one();

        //检查新户主是否是迁入状态
        UserInfo newHouseHoldUser = userInfoService.lambdaQuery().eq(UserInfo::getUsername, houseHold.getHouseholder()).one();
        if (Objects.equals(newHouseHoldUser.getStatus(), "1")&& !Objects.equals(newHouseHoldUser.getHouseholderId(), houseHold.getId())) {
            return Result.failed("户主已经被绑定");
        }


        //更新用户
        //把老户主迁出
        userInfoService.lambdaUpdate()
                .eq(UserInfo::getUsername, oldHouseHold.getHouseholder())
                .set(UserInfo::getStatus,"2")
                .set(UserInfo::getHouseholderId,null)
                .update();

        //把新户主迁入
        userInfoService.lambdaUpdate()
                .eq(UserInfo::getUsername, houseHold.getHouseholder())
                .set(UserInfo::getStatus,"1")
                .set(UserInfo::getHouseholderId,houseHold.getId()).update();

        HouseHold en = new HouseHold();
        BeanUtils.copyProperties(houseHold, en);
        updateById(en);
        return Result.succeed();
    }


}
