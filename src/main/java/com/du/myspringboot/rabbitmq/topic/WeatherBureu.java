package com.du.myspringboot.rabbitmq.topic;

import com.du.myspringboot.rabbitmq.untils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class WeatherBureu {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection=RabbitUtils.getConnection();
        Map area=new LinkedHashMap<String,String>();
        area.put("china.heibei.shijiazhuang.20180811","中国河北石家庄20180811天气");
        area.put("china.shandong.qingdao.20180811","中国山东青岛20180811天气");
        area.put("us.cal.la.20180811","美国加州洛杉矶20180811天气");

        area.put("china.heibei.shijiazhuang.20180812","中国河北石家庄20180812天气");
        area.put("china.shandong.qingdao.20180812","中国山东青岛20180812天气");
        area.put("us.cal.la.20180812","美国加州洛杉矶20180812天气");


        Channel channel=connection.createChannel();
        Iterator<Map.Entry<String,String>> iter=area.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<String,String> me=iter.next();
            //参数：1.交换机 2，队列 3.额外设置属性，消息gbyre[]数组 如1为交换机，2为筛选key
            channel.basicPublish(RabbitUtils.EXCHAGE_WEATHER_TOPIC, me.getKey(),null,me.getValue().getBytes());
        }

        System.out.println("天气订阅推送成功");
        channel.close();
        connection.close();
    }
}
