package com.service;

import com.pojo.PageBean;
import com.pojo.Rating;
import java.util.List;

public interface RatingService {

    int addRating(Rating rating);

    int deleteRatingByIds(int uid,int mid);

    int updateRating(Rating rating);

    Rating queryByIds(int uid,int mid);

    List<Rating> queryUserRatings();

    int selectCount();

    int selectUserCount(int uid);

    PageBean<Rating> findByPage(int currentPage);

    PageBean<Rating> findUserRatingByPage(int currentPage,int uid);
}
