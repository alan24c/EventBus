import EventBusMTO.EventWriteMTO;
import EventBusModel.EventTopic;
import EventBusService.*;
import Metadata.EventBusMetadata;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import EventBusManager.*;

import java.util.List;
import java.util.Set;


/**
 * Created by alan on 17-5-31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-config-test.xml")
public class TestTopicPublish {

    @Autowired
    EventBusService eventBusService;

    @Autowired
    EventBusMetadata eventBusMetadata;

    @Autowired
    EventBusManager eventBusManager;

    @Autowired
    EventBusConsumerService eventBusConsumerService;

    @Test
    @Rollback
    public void testPublishTopic(){


        EventWriteMTO writeMTO = new EventWriteMTO();
        writeMTO.setTopic("test");
        writeMTO.setContext("hello,world");

        int nums = eventBusService.writeEvent(writeMTO);

        Assert.assertEquals(nums,1);
    }

    @Test
    @Rollback
    public void testComsumerTopic(){
        // 构造关系
        String testTopicA = "testTopicA" ;
        String testConsumerBeanA = "processorTest";
        eventBusMetadata.putMetadata(testTopicA,testConsumerBeanA);

        // 写入事件
        ProcessorTestContext context = new ProcessorTestContext();
        eventBusManager.publish(testTopicA,context);

        // 模拟消费
        eventBusConsumerService.dispatchEvent(eventBusConsumerService.getEvents(testTopicA,10));
        eventBusConsumerService.comsumerEvent();


    }

    @Test
    public void testAddTopic(){

        String testTopicA = "testTopicA" ;
        String testTopicB = "testTopicB" ;

        String testConsumerBeanA = "printHelloA";
        String testConsumerBeanB = "printHelloB";


        // 一个 topic 对应 一个 subScribe
        eventBusMetadata.putMetadata(testTopicA,testConsumerBeanA);

        Set<String> topics = eventBusMetadata.getAllTopics();
        Assert.assertEquals( topics.size(),1);

        for(String eventTopic : topics){
            Assert.assertTrue(eventTopic.equals(testTopicA));
        }

        // 一个 topic 对应 多个 subScribe
        eventBusMetadata.putMetadata(testTopicA,testConsumerBeanB);
        Set<String> topics_1 = eventBusMetadata.getAllTopics();
        Assert.assertEquals( topics_1.size(),1);


        for(String eventTopic : topics){
            Assert.assertTrue(eventTopic.equals(testTopicA));
        }

        // 一个 topic 对应 多个 subScirbe
        // 一个 topic 对应 一个 subScribe
        eventBusMetadata.putMetadata(testTopicB,testConsumerBeanB);
        Set<String> topics_2 = eventBusMetadata.getAllTopics();
        Assert.assertEquals( topics_2.size(),2);

    }
}
