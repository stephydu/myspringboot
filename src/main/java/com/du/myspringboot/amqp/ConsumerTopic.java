package com.du.myspringboot.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "queueTopic1")
//@RabbitListener(queues = "hello")
@RabbitListener(queues="quque1")    //监听器监听指定的Queue
public class ConsumerTopic {
//    @RabbitHandler
//    public void recv(String message){
//        System.out.println("接收到最新新闻："+ message);
//    }
//    @RabbitHandler
////    public void process(String hello) {
////        System.out.println("Receiver  : " + hello);
////    }
    @RabbitHandler
public void process1(String str) {
    System.out.println("接受天气1:"+str);
}

}
