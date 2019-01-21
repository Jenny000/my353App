package com.wang.inis.controller;

import com.wang.inis.PostComment.Entity.Comment;
import com.wang.inis.PostComment.repository.IComment;
import com.wang.inis.utils.IPUtils;
import com.wang.inis.utils.VisitorIpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@Slf4j
public class CommentController {
    @Autowired
    private IComment iComment;

    @Autowired
    private IPUtils ipUtils;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  //添加主评论和更新主评论下面的各级评论,如有新的评论，1.存到是数据库 2.更新redis
     * @param c 从前台接收的 Comment
     */

    @RequestMapping(value = "/addComment")
    @CrossOrigin(origins = "*")
    public void addComment(Comment c){
        c.setIp(ipUtils.getIpAddr(request));

        if(c.getParentId().equals("0")){

            iComment.save(c);

        }else {
            String parentId = c.getParentId();
            Comment co = iComment.findCommentByParentId(parentId);
            List<Comment> commentList = co.getChildCommentList();
            commentList.add(c);
            co.setChildCommentList(commentList);
            iComment.save(co);
        }
        List<Comment> allComments = iComment.findAllComments();
        redisTemplate.opsForValue().set("comments",allComments);
        log.info("new comment saved");

    }

    /**
     * 先读redis. 如果为空，读取数据库并存入redis
     * @return 数据库的所有数据
     */

    @RequestMapping(value = "/getComment",method = RequestMethod.GET)
    @CrossOrigin(origins = "*")

    public List<Comment>  getComment(){
        List<Comment> allComments;
        if(redisTemplate.hasKey("comments")){
            allComments= (List<Comment>) redisTemplate.opsForValue().get("comments");
            log.info("read comments from redis");

        }else {
            allComments=iComment.findAllComments();
            log.info("read comments from mysql");
            redisTemplate.opsForValue().set("comments",allComments);

        }

        return allComments;
    }



}
