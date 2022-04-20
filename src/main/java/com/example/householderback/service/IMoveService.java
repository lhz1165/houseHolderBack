package com.example.householderback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.householderback.commom.Result;
import com.example.householderback.entity.Move;

public interface IMoveService extends IService<Move> {
     Result<Void> updateMove(Move param);

     Result<Void> pay(Integer id, Float payment);
}
