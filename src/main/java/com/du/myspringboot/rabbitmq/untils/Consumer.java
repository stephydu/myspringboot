package com.du.myspringboot.rabbitmq.untils;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws IOException {
        //tcp物理连接
        Connection conn=RabbitUtils.getConnection();
        //创建通信 通道
        Channel channel=conn.createChannel();
        channel.queueDeclare(RabbitUtils.QUEUE_NAME,false,false,false,null);
        channel.basicConsume(RabbitUtils.QUEUE_NAME,false,new Reciver(channel));

    }
}
class Reciver extends DefaultConsumer{
    private  Channel channel;
    public Reciver(Channel channel){
        super(channel);
        this.channel=channel;
    }
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

        String messageBody=new String(body);
        System.out.println("消费者接收："+messageBody);
        channel.basicAck(envelope.getDeliveryTag(),false);
    }

}