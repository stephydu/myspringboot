package com.du.myspringboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class MyController {
    @Value("${app.name}")
    private String name;
    @Value("${app.page-size}")
    private Integer pageSize;
    //动态注入IOC容器中匹配的Bean
    @Resource //相同功能可使用@Autowire
    private AppConfig appConfig;

    @RequestMapping("/out")
    @ResponseBody
    public String out(){
        return "success";
    }

    @RequestMapping("/index")
    public String index(){
        System.out.println("热部署");
        return "index";
    }
    @RequestMapping("/date")
    @ResponseBody
    public Date getData(Date date){
        return date;
    }
}
