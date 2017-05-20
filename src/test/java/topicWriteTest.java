import Domain.Event;
import EventBusDAO.EventBusWrite;
import EventBusService.EventBusWriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by alan on 17-5-20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class topicWriteTest {

    @Autowired
    private EventBusWriteService eventBusWriteService;


    @Test
    public void testFindAllStudents() {
        Event event = new Event();
        event.setTopic("test");
        event.setProcessor("testProcessor");
        event.setContext("hello,test");

        eventBusWriteService.writeTopic(event);

    }
}
