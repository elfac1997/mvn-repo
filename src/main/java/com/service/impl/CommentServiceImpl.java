package com.service.impl;

import com.dao.CommentDao;
import com.pojo.Comment;
import com.pojo.PageBean;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public int deleteCommentByIds(int uid, int mid) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("uid",uid);
        map.put("mid",mid);
        return commentDao.deleteCommentByIds(map);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    @Override
    public Comment queryByIds(int uid, int mid) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("uid",uid);
        map.put("mid",mid);
        return commentDao.queryByIds(map);
    }

    @Override
    public List<Comment> queryByUid(int uid) {
        return commentDao.queryByUid(uid);
    }

    @Override
    public List<Comment> queryComments() {
        return commentDao.queryComments();
    }

    @Override
    public int selectCount() {
        return commentDao.selectCount();
    }

    @Override
    public int selectUserCount(int uid) {
        return commentDao.selectUserCount(uid);
    }

    @Override
    public PageBean<Comment> findByPage(int currentPage) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Comment> pageBean = new PageBean<Comment>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=10;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = commentDao.selectCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        //封装每页显示的数据
        List<Comment> lists = commentDao.findByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }

    @Override
    public PageBean<Comment> findUserCommentByPage(int currentPage, int uid) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Comment> pageBean = new PageBean<Comment>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=10;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = commentDao.selectUserCount(uid);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("uid",uid);
        //封装每页显示的数据
        List<Comment> lists = commentDao.findUserCommentByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }
}
