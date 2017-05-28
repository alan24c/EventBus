package EventBusService;

import EventBusMTO.EventReadMTO;
import EventBusModel.Event;
import EventBusModel.EventSubscribe;
import Metadata.EventBusMetadata;
import EventBusProcessor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by alan on 17-5-28.
 */
public class EventBusConsumerService implements ApplicationContextAware{

    private static int nums = 10;

    private volatile boolean isDispatching = true;

    private ApplicationContext applicationContext;

    private ExecutorService comsumerService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);

    @Autowired
    EventBusService eventBusService;

    @Autowired
    EventBusMetadata metadata;

    private BlockingQueue<Event> dispatch = new LinkedBlockingDeque<>();

    public void setApplicationContext(ApplicationContext context){

        this.applicationContext = context;
    }

    // 通过 topic 维度捞取 db 中的 Event,多线程消费 db 中的数据,防止 db 数据积压过多
    // TODO: 17-5-29 增加策略模式,通过不同的策略来进行多线程 db 捞取数据
    public List<Event> getEvents(String topic,int nums){
        EventReadMTO query = new EventReadMTO();

        query.setTopicName(topic);
        query.setNums(nums);

        return eventBusService.getEvent(query);
    }

    public void dispatchEvent(List<Event> events){
        this.dispatch.addAll(events);
    }

    private void comsumerEvent(){

        while (isDispatching){
            Event event = dispatch.poll();
            comsumerService.execute(new consumerTask(event));
        }

    }

    private class consumerTask implements Runnable{

        private Event event;

        consumerTask(Event event){
            this.event = event;
        }

        @Override
        public void run(){
            String topic = event.getTopic();
            String context = event.getContext();

            List<EventSubscribe> subscribes = metadata.getAllSubscribes(topic);
            if(subscribes == null) return;

            for(EventSubscribe subscribe : subscribes){
                String consumerBean = subscribe.getConsumerBeanName();

                EventBusProcessor bean = (EventBusProcessor)applicationContext.getBean(consumerBean);

                EventBusProcessResult res = bean.process(context);

            }
        }

    }


}