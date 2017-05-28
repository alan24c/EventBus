package eventBusSchedule;


import EventBusDAO.EventBusRead;
import EventBusMTO.EventReadMTO;
import EventBusModel.Event;
import EventBusService.*;
import Metadata.EventBusMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alan on 17-5-6.
 */
/*
    EventBus 的触发
    todo:计划用 spring 的定时调度任务
 */
public class EventBusSchedule {

    @Autowired
    EventBusService eventBusService;

    @Autowired
    EventBusConsumerService eventBusConsumerService;

    public void eventSchedule(){

        // db 里面捞取数据
        String topic = "test";
        int nums = 100;
        List<Event> eventList = eventBusConsumerService.getEvents(topic,nums);

        // 派发 event 任务
        eventBusConsumerService.dispatchEvent(eventList);

        // TODO: 17-5-29 通知调度线程成功
    }
}
