package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caopeihe on 2016-8-29.
 */
@Controller
@RequestMapping("direct")
public class Direct {
    @RequestMapping("showPictures.action")
    public String showPictures(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
//        mav.setViewName("jsp/first/showPictures.html");
        return "html/showPictures.html";
    }
}
