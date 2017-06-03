import EventBusProcessor.*;

/**
 * Created by alan on 17-6-4.
 */
public class ProcessorTest implements EventBusProcessor<ProcessorTestContext> {

    public EventBusProcessResult process(ProcessorTestContext context){
        EventBusProcessResult result = new EventBusProcessResult();

        return result;
    }
}
