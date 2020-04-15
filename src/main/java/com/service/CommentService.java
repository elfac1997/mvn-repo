package com.service;

import com.pojo.Comment;
import com.pojo.PageBean;

import java.util.List;

public interface CommentService {

    int addComment(Comment comment);

    int deleteCommentByIds(int uid, int mid);

    int updateComment(Comment comment);

    Comment queryByIds(int uid, int mid);

    List<Comment> queryByUid(int uid);

    List<Comment> queryComments();

    int selectCount();

    int selectUserCount(int uid);

    PageBean<Comment> findByPage(int currentPage);

    PageBean<Comment> findUserCommentByPage(int currentPage,int uid);
}
