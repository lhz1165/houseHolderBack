package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.commom.Result;
import com.example.householderback.dao.UserInfoMapper;
import com.example.householderback.entity.HouseHold;
import com.example.householderback.entity.Move;
import com.example.householderback.entity.User;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.UserInfoPageParam;
import com.example.householderback.entity.vo.UserInfoVo;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.service.IHouseHoldService;
import com.example.householderback.service.IMoveService;
import com.example.householderback.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserInfoServiceServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Autowired
    private AdminUserService userService;
    @Autowired
    private IHouseHoldService houseHoldService;

    @Autowired
    private IMoveService moveService;
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
        userInfo.setUserId(user.getId());
        userInfo.setPaid(true);
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

    @Override
    public List<UserInfo> ListByMove(String moveType,Integer houseId) {
        //1代表迁入 所以我们需要找到迁出的人才可去迁入
        if (Objects.equals(moveType, "1")) {
            return lambdaQuery().eq(UserInfo::getStatus,"2").list();
        }else {
            //2代表迁出 找到指定户籍号找到里面的人，
            // 并且不能是户主
            HouseHold houseHold = houseHoldService.lambdaQuery().eq(HouseHold::getId, houseId).one();
            String householder = houseHold.getHouseholder();
            return lambdaQuery().eq(UserInfo::getStatus,"1")
                    .eq(UserInfo::getHouseholderId,houseId)
                    .ne(UserInfo::getUsername,householder)
                    .list();
        }
    }

    @Override
    public UserInfoVo get(Integer uid) {
        UserInfoVo vo = new UserInfoVo();
        UserInfo userInfo = lambdaQuery().eq(UserInfo::getId, uid).one();
        BeanUtils.copyProperties(userInfo,vo);

        //查询他是不是户主
        HouseHold h = houseHoldService.lambdaQuery().eq(HouseHold::getHouseholder, userInfo.getUsername()).one();
        //迁入 并且金额支付了才算有户籍并且不是户主
        if (Objects.equals(userInfo.getStatus(), "1")&&h==null){
            HouseHold houseHold = houseHoldService.lambdaQuery().eq(HouseHold::getId, userInfo.getHouseholderId()).one();
            if  (houseHold!=null&&userInfo.getPaid()){
                BeanUtils.copyProperties(houseHold,vo);
                vo.setHouseAddress(houseHold.getAddress());
            }
        }
        vo.setAddress(userInfo.getAddress());
        return vo;
    }

    @Override
    public UserInfoVo getByUserName(String username) {
        UserInfoVo vo = new UserInfoVo();
        UserInfo userInfo = lambdaQuery().eq(UserInfo::getUsername, username).one();
        BeanUtils.copyProperties(userInfo,vo);

        //迁入 并且金额支付了才算有户籍
        if (Objects.equals(userInfo.getStatus(), "1")){
            HouseHold houseHold = houseHoldService.lambdaQuery().eq(HouseHold::getId, userInfo.getHouseholderId()).one();
            if (houseHold!=null&&userInfo.getPaid()) {
                BeanUtils.copyProperties(houseHold,vo);
                vo.setHouseAddress(houseHold.getAddress());
            }
        }
        vo.setAddress(userInfo.getAddress());
        return vo;
    }

    @Override
    public void deleteOrRecover(UserInfo user) {
        lambdaUpdate().eq(UserInfo::getId, user.getId()).set(UserInfo::getHouseholderId, null).set(UserInfo::getStatus, user.getStatus()).update();
    }
}
