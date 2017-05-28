package EventBusService;

import Domain.Event;
import EventBusDAO.EventBusWrite;
import EventBusMTO.EventWriteMTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alan on 17-5-20.
 */
@Service
public class EventBusService {

    @Autowired
    EventBusWrite eventBusWrite;

    public int writeEvent(EventWriteMTO event){
//        return eventBusWrite.writeTopic(event);
        return 0;
    }


}
