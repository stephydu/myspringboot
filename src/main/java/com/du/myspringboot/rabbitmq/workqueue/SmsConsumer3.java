package com.du.myspringboot.rabbitmq.workqueue;

import com.du.myspringboot.rabbitmq.untils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class SmsConsumer3 {
    public static void main(String[] args) throws IOException {
        Connection connection=RabbitUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(RabbitUtils.SMS,false,false,false,null);
        //不写basicQus平均分配
        //处理完1个取1个
        channel.basicQos(1);
        channel.basicConsume(RabbitUtils.SMS,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String jsonSMS = new String(body);
                System.out.println("sms-3短信发送成功：" + jsonSMS);
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
