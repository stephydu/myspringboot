package com.du.myspringboot.rabbitmq.untils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {

        //tcp物理连接
        Connection conn=RabbitUtils.getConnection();
        //创建通信 通道
        Channel channel=conn.createChannel();
        channel.queueDeclare(RabbitUtils.QUEUE_NAME,false,false,false,null);
        String message ="hello duyu";
        channel.basicPublish("",RabbitUtils.QUEUE_NAME,null,message.getBytes());
        channel.close();
        conn.close();
        System.out.println("发送数据成功");
    }
}
