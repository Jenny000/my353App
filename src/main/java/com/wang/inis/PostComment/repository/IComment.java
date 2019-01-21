package com.wang.inis.PostComment.repository;

import com.wang.inis.PostComment.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

public interface IComment {
    Comment save (Comment comment);
    Page<Comment> findComment (int page, int size);
    Comment findCommentByParentId(String parentId);
    Comment findAllById(Long id);
    List<Comment> findAllComments();





    

}
