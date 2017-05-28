package EventBusService;


import EventBusDAO.EventBusRead;
import EventBusDAO.EventBusWrite;
import EventBusMTO.EventReadMTO;
import EventBusMTO.EventWriteMTO;
import EventBusModel.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alan on 17-5-20.
 */
@Service
public class EventBusService {

    @Autowired
    EventBusWrite eventBusWrite;

    @Autowired
    EventBusRead eventBusRead;

    public int writeEvent(EventWriteMTO eventWriteMTO){

        Event event = eventWriteMTO.toEvent();

        return eventBusWrite.writeTopic(event);

    }

    public List<Event> getEvent(EventReadMTO query){

        return null;
    }

}
