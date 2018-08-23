package com.du.myspringboot.rabbitmq.untils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitUtils {
    public static final String QUEUE_NAME="hellodu";
    public static final String SMS="sms";
    public static final String EXCHAGE_WEATHER="weather";
    public static final String EXCHAGE_WEATHER_ROUTING="weather_routing";
    public static final String EXCHAGE_WEATHER_TOPIC="weather_topic";
    public static final String QUEUE_BAIDU="weatherbaidu";
    public static final String QUEUE_SINA="weathersina";


    private static ConnectionFactory connectionFactory=new ConnectionFactory();
    static {

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/duMQ");
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = connectionFactory.newConnection();
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
