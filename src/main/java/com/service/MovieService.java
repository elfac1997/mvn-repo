package com.service;

import com.pojo.Movie;
import com.pojo.PageBean;

import java.util.List;

public interface MovieService {

    int addMovie(Movie movie);

    int deleteMovieById(int id);

    int updateMovie(Movie movie);

    Movie queryById(int id);

    List<Movie> queryAllMovies();

    int selectCount();

    PageBean<Movie> findByPage(int currentPage);

    List<Integer> queryMovieByUid(int uid);

}
