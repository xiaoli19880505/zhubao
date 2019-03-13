package com.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Desc:desc
 * @Author:wangli
 * @CreateTime:11:10 2018/10/10
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/totest")
    public String toTest(){
        return "test";
    }
}