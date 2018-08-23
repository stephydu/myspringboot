package com.du.myspringboot.rabbitmq.pubsub;

import com.du.myspringboot.rabbitmq.untils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Baidu {
    public static void main(String[] args) throws IOException {
        Connection connection=RabbitUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(RabbitUtils.QUEUE_BAIDU,false,false,false,null);
        channel.basicQos(1);
        //1.对列名，交换机名，路由keya
        channel.queueBind(RabbitUtils.QUEUE_BAIDU,RabbitUtils.EXCHAGE_WEATHER,"");
        channel.basicConsume(RabbitUtils.QUEUE_BAIDU,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("百度接收信息成功：" + new String(body));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }
}
