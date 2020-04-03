package com.dao;

import com.pojo.Movie;

import java.util.HashMap;
import java.util.List;

public interface MovieDao {
    int addMovie(Movie movie);

    int deleteMovieById(int id);

    int updateMovie(Movie movie);

    Movie queryById(int id);

    List<Movie> queryAllMovies();

    int selectCount();

    List<Movie> findByPage(HashMap<String,Object> map);
}
