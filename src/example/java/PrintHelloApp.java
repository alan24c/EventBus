import Metadata.EventBusMetadata;

import java.util.LinkedList;

/**
 * Created by alan on 17-5-14.
 */
public class PrintHelloApp extends EventBusMetadata {

    public LinkedList<EventSubscribe> getTopicAndSubscribes(){
        EventSubscribe eventSubscribe = new EventSubscribe();
        eventSubscribe.setTopic("PRINT_HELLO");
        eventSubscribe.setConsumerProcessor("printHello");

        LinkedList<EventSubscribe> subscribes = new LinkedList<EventSubscribe>();
        subscribes.add(eventSubscribe);

        return subscribes;
    }

    public static void main(String[] args) {
        PrintHelloApp app = new PrintHelloApp();


    }
}
