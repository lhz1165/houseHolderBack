package com.example.householderback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.householderback.dao.CommentMapper;
import com.example.householderback.entity.Comment;
import com.example.householderback.entity.Reply;
import com.example.householderback.entity.param.AddCommentParam;
import com.example.householderback.entity.param.PageParam;
import com.example.householderback.entity.param.ReplyParam;
import com.example.householderback.entity.vo.CommentVo;
import com.example.householderback.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.householderback.service.IReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhz
 * @since 2022-04-13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private IReplyService replyService;

    @Override
    public void addComment(AddCommentParam param) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(param,comment);
        comment.setCreateTime(LocalDateTime.now());
        save(comment);
    }

    @Override
    public Page<Comment> page(PageParam param) {
        LambdaQueryChainWrapper<Comment> lambdaQuery = lambdaQuery();
        if (param.getUsername() != null) {
            lambdaQuery.eq(Comment::getCommentUser,param.getUsername());
        }
        Page<Comment> page =lambdaQuery.page(new Page<>(param.getCurrent(), param.getPageSize()));
        return page;
    }

    @Override
    public CommentVo get(Integer cid) {
        CommentVo vo = new CommentVo();
        Comment comment = getById(cid);
        Reply reply = replyService.lambdaQuery().eq(Reply::getCid, cid).one();
        if (reply != null) {
            vo.setReply(reply.getReplyComment());
            vo.setReplyTime(reply.getCreateTime());
        }
        BeanUtils.copyProperties(comment,vo);
        return vo;
    }

    @Override
    public void reply(ReplyParam replyParam) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper();
        queryWrapper.eq("cid", replyParam.getCid());
        replyService.remove(queryWrapper);
        Reply reply = new Reply();
        reply.setReplyComment(replyParam.getReply());
        reply.setCid(replyParam.getCid());
        reply.setCreateTime(LocalDateTime.now());
        replyService.save(reply);
    }
}
