import EventBusModel.EventSubscribe;
import Metadata.EventBusMetadata;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Created by alan on 17-6-1.
 */
//@Component
public class EventBusMetadataTest extends EventBusMetadata {

    @Override
    public LinkedList<EventSubscribe> getTopicAndSubscribes(){
        return null;
    }


}
