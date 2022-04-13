package com.example.householderback.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.example.householderback.entity.Comment;
import com.example.householderback.entity.param.AddCommentParam;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.entity.param.ReplyParam;
import com.example.householderback.entity.vo.CommentVo;

public interface ICommentService extends IService<Comment> {
     void addComment(AddCommentParam param);
    Page<Comment> page(PageParam param);

    CommentVo get(Integer cid);

    void reply(ReplyParam replyParam);
}
