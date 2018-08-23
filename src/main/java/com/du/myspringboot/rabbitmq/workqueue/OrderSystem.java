package com.du.myspringboot.rabbitmq.workqueue;

import com.du.myspringboot.rabbitmq.untils.RabbitUtils;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class OrderSystem {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection=RabbitUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(RabbitUtils.SMS,false,false,false,null);
        for(int i=100;i<200;i++){
            Sms sms=new Sms("乘客"+i,"13900000"+i,"您的车票已预订");
            String jsonSms=new Gson().toJson(sms);
            //参数：1.交换机 2，队列 3.额外设置属性，消息gbyre[]数组
            channel.basicPublish("",RabbitUtils.SMS,null,jsonSms.getBytes());
        }
        System.out.println("短信队列推送成功");
        channel.close();
        connection.close();
    }
}
