package com.controller;

import com.pojo.Movie;
import com.pojo.User;
import com.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Controller
@RequestMapping("/movie")
@EnableSwagger2
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/allMovie")
    public String list(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                       int currentPage, Model model) {
        model.addAttribute("pagemsg", movieService.findByPage(currentPage));//回显分页数据
        return "allMovie";
    }

    @RequestMapping("/addMovie")
    public String addMovie(Model model){
        model.addAttribute("returnMovie", new Movie());
        return "editmovie";
    }

    @RequestMapping("/delete")
    public String deleteMovie(@RequestParam("movieId") int id) {
        movieService.deleteMovieById(id);
        return "redirect:/movie/allMovie";
    }

//    @RequestMapping("/updateMovie")
//    public String updateMovie(Model model,Movie movie) {
//        movieService.updateMovie(movie);
//        movie = movieService.queryById(movie.getMovieId());
//        model.addAttribute("movie",movie);
//        return "redict:/movie/allMovie";
//    }

    @RequestMapping("/edit")
    public String updateMovie(@RequestParam("movieId") int id,
                             Model model){
        Movie movie =movieService.queryById(id);
        model.addAttribute("returnMovie", movie);
        System.out.println("**************id*************************"+id);
        System.out.println(movie.toString());
        System.out.println("**************user111*************************");
        return "editmovie";
    }

    @RequestMapping("/save")
    public String save(Movie movie) {
        System.out.println("***************************************");
        System.out.println(movie.toString());
        System.out.println("***************************************");
        if(movie.getMovieId()==null){
            //id为null是保存
            movieService.addMovie(movie);
        }else{
            //有id值为修改
            movieService.updateMovie(movie);
        }
        return "redirect:/movie/allMovie";
    }
}
