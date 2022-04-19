package com.example.householderback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.HouseHold;
import com.example.householderback.entity.param.HouseHoldMoveParam;
import com.example.householderback.entity.param.HouseHoldUpdateParam;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.entity.vo.HouseHoldVo;

public interface IHouseHoldService extends IService<HouseHold> {
    Page<HouseHold> page(PageParam param);

    void addHouseHold(HouseHold houseHold);

    void move(HouseHoldMoveParam param);

    HouseHoldVo get(Integer id);


    Result updateHouseHold(HouseHoldUpdateParam houseHold);
}
