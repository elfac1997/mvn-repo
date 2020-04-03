package com.service;

import com.pojo.PageBean;
import com.pojo.Rating;
import com.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface RatingService {
    int addRating(Rating rating);

    int deleteRatingByIds(int uid,int mid);

    int updateRating(Rating rating);

    Rating queryByIds(int uid,int mid);

    List<Rating> queryUserRatings();

    int selectCount();

    PageBean<Rating> findByPage(int currentPage);
}
