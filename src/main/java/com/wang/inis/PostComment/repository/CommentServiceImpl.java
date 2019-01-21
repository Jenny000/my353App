package com.wang.inis.PostComment.repository;

import com.wang.inis.PostComment.Entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements IComment {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment save(Comment comment) {
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }


    @Override
    public Page<Comment> findComment(int page, int size) {

        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(0,5,sort);
        return commentRepository.findAll(pageable);
    }


    @Override
    public Comment findCommentByParentId(String parentId) {

        return commentRepository.findCommentByParentId(parentId);
    }



    @Override
    public Comment findAllById(Long id) {
        return commentRepository.findAllById(id);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAllComments();
    }

}
