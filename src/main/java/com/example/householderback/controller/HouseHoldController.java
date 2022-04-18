package com.example.householderback.controller;

import com.example.householderback.commom.Result;
import com.example.householderback.entity.HouseHold;
import com.example.householderback.entity.UserInfo;
import com.example.householderback.entity.param.HouseHoldMoveParam;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.entity.param.UserParam;
import com.example.householderback.entity.vo.HouseHoldVo;
import com.example.householderback.service.IHouseHoldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("户籍管理")
@RestController
@RequestMapping("/houseHold")
public class HouseHoldController {
    @Autowired
    private IHouseHoldService houseHoldService;




    @ApiOperation("add")
    @PostMapping("/add")
    public Result<String> add(@RequestBody HouseHold houseHold) {
        houseHoldService.addHouseHold(houseHold);
        return Result.succeed();
    }

    @ApiOperation("get")
    @PostMapping("/get")
    public Result<HouseHoldVo> get(@RequestParam Integer id) {
        return Result.succeed(houseHoldService.get(id));
    }

    @ApiOperation("update")
    @PostMapping("/update")
    public Result<String> update(@RequestBody HouseHold houseHold) {
        houseHoldService.updateHouseHold(houseHold);
        return Result.succeed();
    }


    @ApiOperation("列表")
    @PostMapping("/page")
    public Result page(@RequestBody PageParam param) {
        return Result.succeed(houseHoldService.page(param));
    }

    @PostMapping("/move")
    @ApiOperation("/迁入或迁出")
    public Result move(@RequestBody HouseHoldMoveParam param) {
        houseHoldService.move(param);
        return Result.succeed();
    }


}
