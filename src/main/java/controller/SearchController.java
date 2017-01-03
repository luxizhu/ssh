package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caopeihe on 2017-1-3.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping("searchForm.action")
    public String search(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        return "html/searchForm.html";
    }

    @RequestMapping("searchResult.action")
    public String searchResult(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        return "html/searchForm.html";
    }
}
