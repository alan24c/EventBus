package Metadata;

import EventBusModel.EventSubscribe;
import EventBusModel.EventTopic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by alan on 17-5-6.
 */
/*
    EventBus 的元数据
    a:消息总线 Topic 与 Subscribe 关系的元数据
    由具体的使用方来实现

    b:当前调度队列


 */
// TODO: 17-6-2 为了跑通测试用例删除 abstract
@Component
public class EventBusMetadata implements ApplicationContextAware{
    // 整个 app 中 topic 的订阅关系
    //private ConcurrentHashMap<EventTopic,EventSubscribe> datas = new ConcurrentHashMap<EventTopic, EventSubscribe>();

    private final LinkedList<EventSubscribe> datas = new LinkedList<EventSubscribe>();

    // TODO: 17-5-14 blockingQueue 的使用
    //private BlockingQueue<EventBusBody> dispatchQueue;

    // 订阅关系定义
    private Map<EventTopic,List<EventSubscribe>> relationShip = new ConcurrentHashMap<EventTopic, List<EventSubscribe>>() ;

    private static int THREAD_POOL_NUMS = Runtime.getRuntime().availableProcessors()+1;

    // 消费 event 的线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_NUMS);


    private volatile boolean running = true;

    //public abstract LinkedList<EventSubscribe> getTopicAndSubscribes();

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    // 添加订阅关系
    public void putMetadata(String topicName, String comsunerbean){

        EventTopic topic = new EventTopic(topicName);
        EventSubscribe subscribe = new EventSubscribe(comsunerbean);

        // 新添加的 topic
        if( relationShip.get(topic) == null){
            List<EventSubscribe> subscribes = new LinkedList<EventSubscribe>();
            subscribes.add(subscribe);

            List<EventSubscribe> oldValues = relationShip.putIfAbsent(topic,subscribes);
            // 并发条件下被另外一个线程写入
            if(oldValues != null){
                // TODO: 17-5-28  写入 subscribes 注意线程安全
                oldValues.add(subscribe);
            }
        }else {
            // TODO: 17-5-28 写入 subscribes 注意线程安全
            relationShip.get(topic).add(subscribe);
        }

    }

    public Set<EventTopic> getAllTopics(){
        return relationShip.keySet();
    }

    public List<EventSubscribe> getAllSubscribes(String topic){
        EventTopic eventTopic  = new EventTopic(topic);

        return relationShip.get(eventTopic);
    }

    // 事件的分发,将待分发的任务放入到当前的调度队列中
//    public boolean dispatch(EventBusBody eventBusBody){
//
//        try {
//            dispatchQueue.put(eventBusBody);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }

    // 消费调度队列中的任务
//    private void eventConsumer(){
//
//        Runnable task = new Runnable() {
//            public void run() {
//              while (running){
//                  EventBusBody eventBody = null;
//                  try {
//                      eventBody = dispatchQueue.take();
//
//                      // 获取 topic
//                      String topic = eventBody.getTopic();
//
//                      // 根据 topic 获取消费的 processor
//
//
//                      // TODO: 17-5-14 反序列化
//                      String context = eventBody.getContext();
//
//                      // 消费 event
//
//
//                  } catch (InterruptedException e) {
//                      e.printStackTrace();
//                  }
//              }
//            }
//        };
//
//        for (int i = 0; i < THREAD_POOL_NUMS; i++) {
//            threadPool.submit(task);
//        }
//    }
}
