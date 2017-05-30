package EventBusManager;

import EventBusMTO.EventWriteMTO;
import EventBusService.EventBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by alan on 17-5-28.
 */
@Component
public class EventBusManager {

    @Autowired
    EventBusService eventBusService;

    public void publish(String tpoic, Serializable context){

        EventWriteMTO event = new EventWriteMTO();
        event.setTopic(tpoic);
        event.setContext(context);

        event.validate();

        eventBusService.writeEvent(event);
    }

}
