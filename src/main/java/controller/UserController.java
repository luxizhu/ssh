package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caopeihe on 2016-8-16.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/reg")
    public String register(){
        return "jsp/first/showPictures";
    }
    @RequestMapping("/user")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,User user) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ModelAndView mav=null;
        user.setAge(25);
        mav = new ModelAndView();
        try {
            userService.addUser(user);
            //  request.setAttribute("user", user);
            mav.setViewName("jsp/first/showPictures");
            mav.addObject("user", user);
            mav.addObject("msg", "注册成功了，可以去登陆了");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            mav.setViewName("jsp/first/showPictures");
            mav.addObject("user", null);
            mav.addObject("msg", "注册失败");
            return mav;
        }
    }
}
