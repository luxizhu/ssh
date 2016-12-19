package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caopeihe on 2016-9-9.
 */
@Controller
@RequestMapping("form")
public class FormTest {
    @RequestMapping("test")
    public String getFormVal(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        model.put("result","SUCCESS");
        return "html/formTest.html";
    }
}
