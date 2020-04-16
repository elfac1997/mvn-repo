package com.controller;

import com.pojo.Comment;
import com.pojo.Movie;
import com.pojo.Rating;
import com.pojo.User;
import com.service.CommentService;
import com.service.MovieService;
import com.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.smartcardio.CommandAPDU;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/movie")
@EnableSwagger2
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

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
    public String deleteMovie(@RequestParam("id") int id) {
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
    public String updateMovie(@RequestParam("id") int id,
                             Model model) {
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

    @RequestMapping("/moviedetail")
    public String addComment(@RequestParam("uid") int uid,Model model) {
        List<Movie> randomlist = movieService.queryMovieRandomly();
        Random random=new Random();
        int index = random.nextInt(1000);
        Movie randommovie  = randomlist.get(index);
        System.out.println("randommovie:"+randommovie+"*****************");
        model.addAttribute("randommovie",randommovie );
        model.addAttribute("returncomment", new Comment());
        model.addAttribute("returnrating", new Rating());
        model.addAttribute("userid",uid);
        model.addAttribute("movieid", randommovie.getMovieId());

        return "moviedetail";
    }

    @RequestMapping("/submitComment")
    public String submitComment(Comment comment, Model model){
        System.out.println("comment:"+comment+"***************");

        Long timestamp = System.currentTimeMillis();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String time = simpleDateFormat.format(new Date(timestamp));

        comment.setTimeStamp(time);
        System.out.println("comment:"+comment+"*************");
        commentService.addComment(comment);
        System.out.println("comment submti success +1*************");
        //model.addAttribute("userid",comment.getUserId());
        return "redirect:/movie/moviedetail?uid="+comment.getUserId();
    }

    @RequestMapping("/userComment")
    public String listuserComment(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                          int currentPage,
                                  @RequestParam(value="uid")int uid, Model model) {
        model.addAttribute("pagemsg", commentService.findUserCommentByPage(currentPage,uid));//回显分页数据
        System.out.println(commentService.findUserCommentByPage(currentPage,uid)+"********************");
        model.addAttribute("uid",uid);
        return "usercomment";
    }

    @RequestMapping("/allComment")
    public String listallComment(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                         int currentPage, Model model) {
        model.addAttribute("pagemsg", commentService.findByPage(currentPage));//回显分页数据
        return "allcomments";
    }

    @RequestMapping("/editComment")
    public String updateComment(@RequestParam("uid") int uid,@RequestParam("mid") int mid,
                             Model model){
        Comment comment = commentService.queryByIds(uid,mid);
        model.addAttribute("returnComment", comment);
        System.out.println("***************************************");
        System.out.println(comment.toString());
        System.out.println("***************************************");
        return "editcomment";
    }

//    @RequestMapping("/saveComment")
//    public String saveComment(@RequestParam(value="uid")int uid,Comment comment ) {
//        System.out.println("***********saving comment**********************");
//        System.out.println(comment.toString());
//        System.out.println("************saving comment*********************");
//
//        Long timestamp = System.currentTimeMillis();
//        String pattern = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String time = simpleDateFormat.format(new Date(timestamp));
//        comment.setTimeStamp(time);
//
//        System.out.println("************timestamp:"+time+"*********************");
//        commentService.updateComment(comment);
//
//        return "redirect:/movie/userComment?uid="+uid;
//    }

    @RequestMapping("/deleteComment")
    public String deleteRating(@RequestParam("uid") int uid,@RequestParam("mid") int mid) {
        commentService.deleteCommentByIds(uid,mid);
        return "redirect:/movie/allComment";
    }

    @RequestMapping("/deleteuserCommet")
    public String deleteuserRating(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                           int currentPage,
                                   @RequestParam("uid") int uid,
                                   @RequestParam("mid") int mid,
                                   Model model) {
        model.addAttribute("pagemsg", commentService.findUserCommentByPage(currentPage,uid));//回显分页数据\
        model.addAttribute("uid",uid);
        commentService.deleteCommentByIds(uid,mid);
        return "userComment";
    }

}
