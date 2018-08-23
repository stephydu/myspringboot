package com.du.myspringboot.rabbitmq.pubsub;

import com.du.myspringboot.rabbitmq.untils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class WeatherBureu {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection=RabbitUtils.getConnection();
        //控制台输入消息
        String input=new Scanner(System.in).next();
//        String input="天气测试信息";
        Channel channel=connection.createChannel();
        //参数：1.交换机 2，队列 3.额外设置属性，消息gbyre[]数组
        channel.basicPublish(RabbitUtils.EXCHAGE_WEATHER,"",null,input.getBytes());
        System.out.println("天气订阅推送成功");
        channel.close();
        connection.close();
    }
}
