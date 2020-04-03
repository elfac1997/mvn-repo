package com.controller;


import com.pojo.Rating;
import com.pojo.User;
import com.service.RatingService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@EnableSwagger2
@Controller
@SessionAttributes("currentUser")
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/allUser")
    public String list(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                   int currentPage, Model model) {
        model.addAttribute("pagemsg", userService.findByPage(currentPage));//回显分页数据
        return "allUser";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("returnUser", new User());
        return "edit";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/user/allUser";
    }

    @RequestMapping("/edit")
    public String updateUser(@RequestParam("id") int id,
                             Model model){
        User user =userService.queryUserById(id);
        model.addAttribute("returnUser", user);
        System.out.println("**************id*************************"+id);
        System.out.println(user.toString());
        System.out.println("**************user111*************************");
        return "edit";
    }

    @RequestMapping("/save")
    public String save(User user) {
        System.out.println("***************************************");
        System.out.println(user.toString());
        System.out.println("***************************************");
        if(user.getId()==null){
            //id为null是保存
            userService.addUser(user);
        }else{
            //有id值为修改
            userService.updateUser(user);
        }
        return "redirect:/user/allUser";
    }

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {

        //获取当前用户
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try{
            //为当前用户进行认证，授权
            subject.login(token);
            request.setAttribute("user", user);
            return "success";

        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("user", user);
            //request.setAttribute("errorMsg", "用户名或密码错误！");
            return "login";
        }
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("/editRating")
    public String updateRating(@RequestParam("uid") int uid,
                               @RequestParam("mid") int mid,
                             Model model){
        Rating rating =ratingService.queryByIds(uid,mid);
        model.addAttribute("returnRating", rating);
        System.out.println("**************editrating*************************");
        System.out.println(rating.toString());
        System.out.println("**************editrating************************");
        return "editrating";
    }

    @RequestMapping("/addRating")
    public String addRating(Model model){
        model.addAttribute("returnRating", new Rating());
        return "editrating";
    }

    @RequestMapping("/deleteRating")
    public String deleteRating(@RequestParam("uid") int uid,@RequestParam("mid") int mid) {
        ratingService.deleteRatingByIds(uid,mid);
        return "redirect:/user/allRating";
    }

    @RequestMapping("/deleteuserRating")
    public String deleteuserRating(@RequestParam("uid") int uid,@RequestParam("mid") int mid) {
        ratingService.deleteRatingByIds(uid,mid);
        return "redirect:/user/userRating";
    }


    @RequestMapping("/userRating")
    public String listuserrating(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                     int currentPage, Model model) {
        model.addAttribute("pagemsg", userService.findByPage(currentPage));//回显分页数据
        return "userRating";
    }

    @RequestMapping("/allRating")
    public String listrating(@RequestParam(value="currentPage",defaultValue="1",required=false)
                               int currentPage, Model model) {
        model.addAttribute("pagemsg", userService.findByPage(currentPage));//回显分页数据
        return "allRating";
    }
}
