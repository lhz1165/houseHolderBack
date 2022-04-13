package com.example.householderback.controller;


import com.example.householderback.commom.Result;
import com.example.householderback.entity.param.AddCommentParam;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.entity.param.ReplyParam;
import com.example.householderback.service.ICommentService;
import com.example.householderback.service.IReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("意见反馈")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IReplyService replyService;

    @ApiOperation("add")
    @PostMapping("/add")
    public Result addComment(@RequestBody AddCommentParam param) {
        commentService.addComment(param);
        return Result.succeed();
    }

    @ApiOperation("get")
    @GetMapping("/get")
    public Result get(@RequestParam Integer cid) {
        return Result.succeed(commentService.get(cid));
    }

    @ApiOperation("page")
    @PostMapping("/page")
    public Result page(@RequestBody PageParam param) {
        return Result.succeed(commentService.page(param));
    }


    @ApiOperation("delete")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer cid) {
        commentService.removeById(cid);
        return Result.succeed();
    }

    @ApiOperation("reply")
    @PostMapping("/reply")
    public Result reply(@RequestBody ReplyParam replyParam) {
        commentService.reply(replyParam);
        return Result.succeed();
    }

}
