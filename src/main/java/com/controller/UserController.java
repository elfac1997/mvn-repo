package com.controller;


import com.pojo.Movie;
import com.pojo.Rating;
import com.pojo.User;
import com.service.MovieService;
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

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableSwagger2
@Controller
@SessionAttributes("currentUser")
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

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
    public String updateUser(@RequestParam("uid") int id,
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
        String name = user.getUsername();
        if(user.getId()!= null) {
            //有id值为修改
            userService.updateUser(user);
        }else if(user.getRoleId()!= null) {
            //admin register
            if(userService.getByUserName(name)!=null) {
                // register failed
                return "fail";
            }
            System.out.println("**********admin register user or admin***********************");
            userService.addUser(user);
            System.out.println("***********admin success registered*****************");
        }else if(user.getRoleId() == null) {//user register
            System.out.println("**********user register user***********************");
            if(userService.getByUserName(name)!=null) {
                // register failed
                return "fail";
            }else if(userService.getByUserName(name) == null) {
                user.setRoleId(2);
                userService.registerUser(user);
                System.out.println("***********user success registered*****************");
                return "redirect:/user/login";
            }
        }else {
            return "fail";
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

    @RequestMapping("/success")
    public String toSuceess(@RequestParam("uid") int uid,Model model) {
        System.out.println("*************menu clicked*********uid:"+uid);
        model.addAttribute("userid",uid);
        return "success";
    }

    @RequestMapping("/menu")
    public String menu(@RequestParam("uid") int uid,Model model) {
        System.out.println("*************menu clicked*********uid:"+uid);
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
    public String deleteuserRating(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                               int currentPage,
                                   @RequestParam("uid") int uid,
                                   @RequestParam("mid") int mid,
                                   Model model) {
        model.addAttribute("pagemsg", ratingService.findUserRatingByPage(currentPage,uid));//回显分页数据\
        model.addAttribute("uid",uid);
        ratingService.deleteRatingByIds(uid,mid);
        return "userRating";
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
            return "redirect:/user/userRating?uid="+rating.getUserId();
        }
        return "redirect:/user/allRating";
    }

    @RequestMapping("/submitToSpark")
    public String submitToSpark(@RequestParam("uid") int uid){
        submitToSparkandLoad(uid);
        return "recommend";

    }

    @RequestMapping("/recommend")
    public String recommend(@RequestParam("uid") int uid, Model model){
        List<Integer> movieids = movieService.queryMovieByUid(uid);
        List<Movie> movierecomemnd = new ArrayList<>();
        for(int i : movieids){
            movierecomemnd.add(movieService.queryById(i));
        }
        model.addAttribute("list",movierecomemnd);
        return "recommend";
    }


    public void submitToSparkandLoad(int uid) {
        String shellString = "spark-submit --class recommend.MovieLensALS ~/IdeaProjects/Film_Recommend_Dataframe/out/artifacts/Film_Recommend_Dataframe_jar/Film_Recommend_Dataframe.jar  /input_spark"+" "+"24";
        System.out.println("shellString:"+shellString);
        StringBuilder result = new StringBuilder();

        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;

        try {
            String[] commond = {"/bin/sh","-c",shellString};
// 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(commond);

// 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();

// 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));

// 读取输出
            String line = null;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append('\n');
            }
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append('\n');
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeStream(bufrIn);
            closeStream(bufrError);

// 销毁子进程
            if (process != null) {
                process.destroy();
            }
        }
    System.out.println(result.toString());
// 返回执行结果
        //return result.toString();
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
// nothing
            }
        }
        }
}
