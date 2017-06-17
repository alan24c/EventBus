package EventBusService;

import EventBusMTO.EventReadMTO;
import EventBusModel.Event;
import EventBusModel.EventSubscribe;
import Metadata.EventBusMetadata;
import EventBusProcessor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import utils.FastJsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by alan on 17-5-28.
 */
@Component
public class EventBusConsumerService implements ApplicationContextAware{

    private static int nums = 10;

    private volatile boolean isDispatching = true;

    @Autowired
    private ApplicationContext applicationContext;

    private ExecutorService comsumerService = Executors.newFixedThreadPool(1);

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

    public void comsumerEvent(){

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

        public void run(){
            String topic = event.getTopic();
            Integer topicNums = 0; // TODO
            String context = event.getContext();
            int consumerMask = event.getConsumerMask();

            // 获取需要执行的 processor 的位置信息
            List<Integer> locations = calculateLocation(topicNums,consumerMask);


            List<EventSubscribe> subscribes = metadata.getAllSubscribes(topic);
            if(subscribes == null) return;


            for(Integer index: locations){
                EventSubscribe subscribe = subscribes.get(index);
                String consumerBean = subscribe.getConsumerBeanName();

                EventBusProcessor bean = (EventBusProcessor)applicationContext.getBean(consumerBean);
                Object obj = FastJsonUtils.stringToObject(context);

                EventBusProcessResult res = bean.process(obj);

                if(res.isSuccess()){
                    event.setConsumerMask(maskProcessSuccess(consumerMask,index));
                }

            }

            // DB 入库
            

        }

    }

    /*
       nums: 0000 1111
       mask: 0000 0101
       return ： 1,3
    */
    private List<Integer> calculateLocation(int nums, int mask){
        List<Integer> res = new ArrayList<>();

        int temp = nums & mask;
        for(int i = 1; i< Integer.MAX_VALUE;i++){
            int result = temp & 0x00000001;

             if(result != 0x00000000){
                 res.add(i);
             }else{
                 break;
             }

             temp = temp>>1;
        }

        return res;
    }


    /*
       mask: 0000 0101
       index: 3
       return ： 0000 0001
    */
    private int maskProcessSuccess(int mask,int index){

        int temp = 0x00000001;
        temp = temp << (index-1);
        return temp^mask;
    }
}
