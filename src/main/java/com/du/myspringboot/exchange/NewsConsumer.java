package com.du.myspringboot.exchange;

import com.du.myspringboot.entity.News;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class NewsConsumer {
    public void recv(News news){
        System.out.println("接收到最新新闻："+ new Gson().toJson(news));
    }
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

    }
}
