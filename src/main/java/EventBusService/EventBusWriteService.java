package EventBusService;

import Domain.Event;
import EventBusDAO.EventBusWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alan on 17-5-20.
 */
@Service
public class EventBusWriteService {

    @Autowired
    EventBusWrite eventBusWrite;

    public int writeTopic(Event event){
        return eventBusWrite.writeTopic(event);
    }
}
