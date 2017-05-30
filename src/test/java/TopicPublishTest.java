import EventBusMTO.EventWriteMTO;
import EventBusService.EventBusService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by alan on 17-5-31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TopicPublishTest {

    @Autowired
    EventBusService eventBusService;

    @Test
    @Rollback
    public void testPublishTopic(){

        EventWriteMTO writeMTO = new EventWriteMTO();
        writeMTO.setTopic("test");
        writeMTO.setContext("hello,world");

        int nums = eventBusService.writeEvent(writeMTO);

        Assert.assertEquals(nums,1);
    }
}
