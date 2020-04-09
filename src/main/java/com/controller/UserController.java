package com.controller;


import com.pojo.Rating;
import com.pojo.User;
import com.service.RatingService;
import com.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.spark.deploy.SparkSubmit;
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

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping("/registerUser")
    public String registerUser(Model model){
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
        if(user.getId()!=null){
            //有id值为修改
            if(userService.getByUserName(user.getUsername())!=null){
                return "fail";
            }
            if(user.getRoleId() == null){
                //user change username or password
                user.setRoleId(2);
            }
            userService.updateUser(user);
        }else if(user.getRoleId()!=null){
            //id为null是保存
            userService.addUser(user);
        }else {
            System.out.println("**********registeruser***********************");
            System.out.println(user.toString());
            System.out.println("*************registeruser********************");
            String name = user.getUsername();
            if(userService.getByUserName(name)!=null) {
                // register failed
                return "fail";
            }else {
                userService.registerUser(user);
                return "redirect:/user/login";
            }

        }
        return "redirect:/user/allUser";
    }

    @RequestMapping("/toLogin")
        public String toLogin(){
            return "login";
        }


    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {

        //获取当前用户
        Subject subject=SecurityUtils.getSubject();
        if(subject.isAuthenticated() == false){
            UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
            System.out.println("************user:"+user.toString()+"***********");
            User u = userService.getByUserName(user.getUsername());
            System.out.println("************u:"+u.toString()+"***********");
            try{
                //为当前用户进行认证，授权
                subject.login(token);
                request.setAttribute("user", u);
                request.setAttribute("userid", u.getId());
                System.out.println("***success*******user:"+u.toString()+"********user.id:"+u.getId()+"*******");
                request.setAttribute("successMsg", "login success！");
            }catch(AuthenticationException e){
                e.printStackTrace();
                request.setAttribute("user", u);
                System.out.println("***error*******u:"+u.toString()+"********u.id:"+u.getId()+"*******");
                request.setAttribute("errorMsg", "用户名或密码错误！");
                return "login";
            }

        }
        return "success";
    }

    @RequestMapping("/menu")
    public String menu(@RequestParam("uid") int uid,Model model) {
        model.addAttribute("userid",uid);
        return "success";
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
                                     int currentPage,
                                 @RequestParam(value="uid")int uid, Model model) {
        model.addAttribute("pagemsg", ratingService.findUserRatingByPage(currentPage,uid));//回显分页数据\
        model.addAttribute("uid",uid);
        return "userRating";
    }

    @RequestMapping("/allRating")
    public String listrating(@RequestParam(value="currentPage",defaultValue="1",required=false)
                               int currentPage, Model model) {
        model.addAttribute("pagemsg", ratingService.findByPage(currentPage));//回显分页数据
        return "allRating";
    }

    @RequestMapping("/saveRating")
    public String saveRating(Rating rating) {
        System.out.println("***********saving rating**********************");
        System.out.println(rating.toString());
        System.out.println("************saving rating*********************");

        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=sdf.format(date);
        rating.setTimeStamp(dateStr);

        System.out.println("************timestamp:"+rating.getTimeStamp()+"*********************");
        ratingService.updateRating(rating);
        int rid = userService.queryUserById(rating.getUserId()).getRoleId();
        if(rid == 2) {
            return "redirect:/user/userRating";
        }
        return "redirect:/user/allRating";
    }

//    @RequestMapping("/submitToSpark")
//    public String submitToSpark(@RequestParam("uid") int userid) {
//        System.out.println("userid is :"+userid);
//        System.out.println("***********starting submit to spark**********************");
//        String[] args = {
//                "--class", "recommend.MovieLensALS",
//                "--master", "spark://master:7077",
//                "--deploy-mode", "cluster",
//                "--executor-memory", "1g",
//                "--total-executor-cores", "2",
//                "hdfs://localhost:9000/jars/Film_Recommend_Dataframe.jar",
//                "hdfs:///input_spark",
//                "1"
//        };
//
//        SparkSubmit.main(args);
//        System.out.println("************submit finished*********************");
//        return "redirect:/user/allRating";
//    }
        @RequestMapping("/submitToSpark")
        public String submitToSpark() {
            System.out.println("***********redirect to 3000**********************");
            return "redirect:http://localhost:3000";
        }
}
