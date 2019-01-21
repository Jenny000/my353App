package com.wang.inis.PostComment.repository;

import com.wang.inis.PostComment.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    Comment save (Comment comment);
/**
    @Modifying 此时在@Query注解中定义，必须加上@Modify,告诉spring data 这是一个update/delete操作。
    @Transactional //不加会出现错误     javax.persistence.TransactionRequiredException: Executing an update/delete query
    @Query(value = "select * from comment where create_time between fromDate and toDate",nativeQuery = true)
    List<Comment> findByDate(Date fromDate, Date toDate);
**/

    Page<Comment> findAll(Pageable pageable);

    @Transactional
    @Query(value = "select * from comment where id = ?", nativeQuery = true)
    Comment findCommentByParentId(String parentId);


    Comment findAllById(Long id);


    @Transactional
    @Query(value = "select * from comment  where parent_id ='0' order by id DESC" , nativeQuery = true)
    List<Comment> findAllComments();





}
