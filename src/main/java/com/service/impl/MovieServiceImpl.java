package com.service.impl;

import com.dao.MovieDao;
import com.pojo.Movie;
import com.pojo.PageBean;
import com.pojo.User;
import com.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao moviedao;

    public int updateMovie(Movie movie) {
        return moviedao.updateMovie(movie);
    }

    public int addMovie(Movie movie){
       return  moviedao.addMovie(movie);
    }

    public int deleteMovieById(int id) {
        return moviedao.deleteMovieById(id);
    }

    public Movie queryById(int id) {
        return moviedao.queryById(id);
    }

    public List<Movie> queryAllMovies() {
        return moviedao.queryAllMovies();
    }

    public int selectCount() {
        return moviedao.selectCount();
    }

    public PageBean<Movie> findByPage(int currentPage) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Movie> pageBean = new PageBean<Movie>();

        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=10;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = moviedao.selectCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        //封装每页显示的数据
        List<Movie> lists = moviedao.findByPage(map);
        pageBean.setLists(lists);

        return pageBean;
    }
}
