package com.dao;

import com.pojo.Rating;

import java.util.HashMap;
import java.util.List;

public interface RatingDao {
    int addRating(Rating rating);

    int deleteRatingByIds(int uid,int mid);

    int updateRating(Rating rating);

    Rating queryByIds(HashMap<String,Object> map);

    List<Rating> queryUserRatings();

    int selectCount();

    int selectUserCount(int uid);

    List<Rating> findByPage(HashMap<String,Object> map);

    List<Rating> findUserRatingByPage(HashMap<String,Object> map);
}
