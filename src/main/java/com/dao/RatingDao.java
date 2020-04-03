package com.dao;

import com.pojo.Rating;

import java.util.HashMap;
import java.util.List;

public interface RatingDao {
    int addRating(Rating rating);

    int deleteRatingByIds(int uid,int mid);

    int updateRating(Rating rating);

    Rating queryByIds(int uid,int mid);

    List<Rating> queryUserRatings();

    int selectCount();

    List<Rating> findByPage(HashMap<String,Object> map);
}
