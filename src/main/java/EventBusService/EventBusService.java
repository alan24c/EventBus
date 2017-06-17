package EventBusService;


import EventBusDAO.EventBusRead;
import EventBusDAO.EventBusWrite;
import EventBusMTO.EventReadMTO;
import EventBusMTO.EventUpdateMTO;
import EventBusMTO.EventWriteMTO;
import EventBusModel.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alan on 17-5-20.
 */
@Component
public class EventBusService {

    @Autowired
    private EventBusWrite eventBusWrite;

    @Autowired
    private EventBusRead eventBusRead;

    public int writeEvent(EventWriteMTO eventWriteMTO){

        Event event = eventWriteMTO.toEvent();

        return eventBusWrite.writeTopic(event);

    }

    public List<Event> getEvent(EventReadMTO query){

        return eventBusRead.getEvent(query);
    }

    public int updateEvent(EventUpdateMTO eventWriteMTO){

        return 1;
    }

}
