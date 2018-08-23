package com.du.myspringboot;

import com.du.myspringboot.amqp.ConsumerTopic;
import com.du.myspringboot.amqp.ProducerTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyspringbootApplicationTests {

    @Autowired
    private ProducerTopic producerTopic;

    @Test
    public void testRabbit(){
       producerTopic.send1();
    }

//    @Test
//    public void contextLoads() {
//    }

}
