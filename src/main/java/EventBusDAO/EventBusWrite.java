package EventBusDAO;

import EventBusModel.Event;

/**
 * Created by alan on 17-5-6.
 */
public interface EventBusWrite {

    public int writeTopic(Event event);

    public int updateEvent(Event event);
}
