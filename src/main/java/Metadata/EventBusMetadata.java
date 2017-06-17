package Metadata;

import EventBusModel.EventSubscribe;
import EventBusModel.EventTopic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;
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
public abstract class EventBusMetadata implements ApplicationContextAware{
    // 整个 app 中 topic 的订阅关系
    //private ConcurrentHashMap<EventTopic,EventSubscribe> datas = new ConcurrentHashMap<EventTopic, EventSubscribe>();

    private final LinkedList<EventSubscribe> datas = new LinkedList<EventSubscribe>();

    // TODO: 17-5-14 blockingQueue 的使用
    //private BlockingQueue<EventBusBody> dispatchQueue;

    // 订阅关系定义
    private Map<String,List<EventSubscribe>> relationShip = new ConcurrentHashMap<String, List<EventSubscribe>>() ;

    // 主题的定义
    private Set<EventTopic> topics = new HashSet<>();

    private static int THREAD_POOL_NUMS = Runtime.getRuntime().availableProcessors()+1;

    // 消费 event 的线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_NUMS);


    private volatile boolean running = true;

    public abstract LinkedList<EventSubscribe> getTopicAndSubscribes();

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    // 添加 topic
    public void registTopic(String topicName,Integer processorNums){
        EventTopic topic = new EventTopic(topicName,processorNums);
        topics.add(topic);
    }

    // 添加订阅关系
    public void putMetadata(String topicName, String comsunerbean,int comsumerIndex){



        EventSubscribe subscribe = new EventSubscribe(comsunerbean,comsumerIndex);

        // 新添加的 topic
        if( relationShip.get(topicName) == null){
            List<EventSubscribe> subscribes = new LinkedList<EventSubscribe>();
            subscribes.add(subscribe);

            List<EventSubscribe> oldValues = relationShip.putIfAbsent(topicName,subscribes);
            // 并发条件下被另外一个线程写入
            if(oldValues != null){
                // TODO: 17-5-28  写入 subscribes 注意线程安全
                oldValues.add(subscribe);
            }
        }else {
            // TODO: 17-5-28 写入 subscribes 注意线程安全
            relationShip.get(topicName).add(subscribe);
        }

    }

    public Set<String> getAllTopics(){
        return relationShip.keySet();
    }

    public List<EventSubscribe> getAllSubscribes(String topic){

        return relationShip.get(topic);
    }

}
