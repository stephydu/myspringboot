package com.du.myspringboot.amqp;

import com.du.myspringboot.entity.News;
import com.du.myspringboot.rabbitmq.untils.RabbitUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ProducerTopic {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
    public void send1() {
        this.rabbitTemplate.convertAndSend("exchange","china.heibei.shijiazhuang.20180811","中国河北石家庄20180811天气");
        this.rabbitTemplate.convertAndSend("exchange","china.shandong.qingdao.20180811","中国山东青岛20180811天气");
        this.rabbitTemplate.convertAndSend("exchange","us.cal.la.20180811","美国加州洛杉矶20180811天气");
        this.rabbitTemplate.convertAndSend("exchange","china.heibei.shijiazhuang.20180812","中国河北石家庄20180812天气");
        this.rabbitTemplate.convertAndSend("exchange","china.shandong.qingdao.20180812","中国山东青岛20180812天气");
        this.rabbitTemplate.convertAndSend("exchange","us.cal.la.20180812","美国加州洛杉矶20180812天气");
        System.out.println("天气发送成功  " );
    }

}
