package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by caopeihe on 2016-8-29.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("tryStr.action")
    public String tryStr(){
        return "html/index.html";
    }

}
