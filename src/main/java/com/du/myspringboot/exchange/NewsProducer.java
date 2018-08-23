package com.du.myspringboot.exchange;

import com.du.myspringboot.entity.News;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class NewsProducer {
    private RabbitTemplate rabbitTemplate = null;

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNews(String routingkey,News news){
//        convertAndSend 用于向exchange发送数据
//        参数1是routingkey
//        参数2是要传递的对象，可以使字符串，byte[]或任何实现了【序列化接口】的对象
        rabbitTemplate.convertAndSend(routingkey,news);
        System.out.println("新闻发送成功");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        NewsProducer np=(NewsProducer)ctx.getBean("newsProducer");
        np.sendNews("us.20180812",new News("环球时报" ,"rabbitmq好",new Date(),"国际新闻"));
        np.sendNews("china.20180812",new News("新华社" ,"springboot好",new Date(),"国内新闻"));
    }
}
