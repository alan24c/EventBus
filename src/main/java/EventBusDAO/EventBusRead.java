package EventBusDAO;

import EventBusMTO.EventReadMTO;
import EventBusModel.Event;

import java.util.List;

/**
 * Created by alan on 17-5-6.
 */
public interface EventBusRead {

    List<Event> getEvent(EventReadMTO readMTO);
}
