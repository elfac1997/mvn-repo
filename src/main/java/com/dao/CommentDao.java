package com.dao;

import com.pojo.Comment;

import java.util.HashMap;
import java.util.List;

public interface CommentDao {

    int addComment(Comment comment);

    int deleteCommentByIds(HashMap<String,Integer> map);

    int updateComment(Comment comment);

    Comment queryByIds(HashMap<String,Object> map);

    List<Comment> queryByUid(int uid);

    List<Comment> queryComments();

    int selectCount();

    int selectUserCount(int uid);

    List<Comment> findByPage(HashMap<String,Object> map);

    List<Comment> findUserCommentByPage(HashMap<String,Object> map);

}
