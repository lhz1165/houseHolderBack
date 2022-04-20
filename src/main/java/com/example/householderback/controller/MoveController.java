package com.example.householderback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.Move;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.service.IMoveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("move")
@RequestMapping("/move")
public class MoveController {
    @Autowired
    private IMoveService moveService;

    @ApiOperation("add")
    @PostMapping("/add")
    public Result addComment(@RequestBody Move param) {
        return moveService.updateMove(param);
    }

    @ApiOperation("get")
    @GetMapping("/get")
    public Result get(@RequestParam Integer cid) {
        return Result.succeed();
    }

    @ApiOperation("page")
    @PostMapping("/page")
    public Result<Page<Move>> page(@RequestBody PageParam param) {
        Page<Move> page = moveService.lambdaQuery()
                .eq(StringUtils.hasLength(param.getUsername()),Move::getUsername,param.getUsername())
                .orderByDesc(Move::getCreateTime)
                .page(new Page<>(param.getCurrent(), param.getPageSize()));
        return Result.succeed(page);
    }

    @ApiOperation("pay")
    @GetMapping("/pay")
    public Result<Void> payMove(@RequestParam Integer id,@RequestParam(required = false)Float payment) {
        return moveService.pay(id, payment);
    }

}
