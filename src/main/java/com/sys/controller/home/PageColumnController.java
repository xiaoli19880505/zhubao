package com.sys.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("pageColumn")
public class PageColumnController {

    private final String pageHome = "home";

    private final String pageHomeExternal = "home/external";

    /**
     * 显示后台子页面
     * @param page
     * @return
     */
    @RequestMapping("{page}")
    public String showPage(@PathVariable String page, HttpSession session) {
        return pageHome + "/" + page;
    }

    /**
     * 显示后台子页面
     * @param page
     * @return
     */
    @RequestMapping("external/{page}")
    public String showPageExternal(@PathVariable String page, HttpSession session) {
        return pageHomeExternal + "/" + page;
    }

    @RequestMapping("dataTag")
    public String showDataTag(){
        return "WordDataTag2/DataTag";
    }

}
