package com.example.householderback.controller;

import com.example.householderback.commom.Result;
import com.example.householderback.entity.HouseHold;
import com.example.householderback.entity.param.HouseHoldMoveParam;
import com.example.householderback.entity.param.HouseHoldUpdateParam;
import com.example.householderback.entity.param.PageParam;
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
    @GetMapping("/get")
    public Result<HouseHoldVo> get(@RequestParam Integer id) {
        return Result.succeed(houseHoldService.get(id));
    }


    @ApiOperation("update")
    @PostMapping("/update")
    public Result update(@RequestBody HouseHoldUpdateParam houseHold) {
        return  houseHoldService.updateHouseHold(houseHold);
    }


    @ApiOperation("列表")
    @PostMapping("/page")
    public Result page(@RequestBody PageParam param) {
        return Result.succeed(houseHoldService.page(param));
    }


}
