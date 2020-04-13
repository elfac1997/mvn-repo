package com.service.impl;

import com.dao.RatingDao;
import com.pojo.PageBean;
import com.pojo.Rating;
import com.pojo.User;
import com.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDao ratingDao;

    @Override
    public int addRating(Rating rating) {
        return ratingDao.addRating(rating);
    }

    @Override
    public int deleteRatingByIds(int uid, int mid) {
        return ratingDao.deleteRatingByIds(uid, mid);
    }

    @Override
    public int updateRating(Rating rating) {
        return ratingDao.updateRating(rating);
    }

    @Override
    public Rating queryByIds(int uid, int mid) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("uid",uid);
        map.put("mid",mid);
        return ratingDao.queryByIds(map);
    }

    @Override
    public List<Rating> queryUserRatings() {
        return ratingDao.queryUserRatings();
    }

    @Override
    public int selectCount() {
        return ratingDao.selectCount();
    }

    @Override
    public int selectUserCount(int uid){
        return ratingDao.selectUserCount(uid);
    }

    @Override
    public PageBean<Rating> findByPage(int currentPage) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Rating> pageBean = new PageBean<Rating>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=10;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = ratingDao.selectCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        //封装每页显示的数据
        List<Rating> lists = ratingDao.findByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }

    @Override
    public PageBean<Rating> findUserRatingByPage(int currentPage,int uid) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Rating> pageBean = new PageBean<Rating>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=5;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = ratingDao.selectUserCount(uid);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("uid",uid);
        //封装每页显示的数据
        List<Rating> lists = ratingDao.findUserRatingByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }
}
