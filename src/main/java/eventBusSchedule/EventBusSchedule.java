package eventBusSchedule;

import EventBusDAO.EventBusRead;
import Metadata.EventBusMetadata;

/**
 * Created by alan on 17-5-6.
 */
/*
    EventBus 的触发
    todo:计划用 spring 的定时调度任务
 */
public class EventBusSchedule {

    private EventBusMetadata eventBusMetadata;

    private EventBusRead eventBusRead;

    public void eventSchedule(){

        // db 里面捞取数据


        // 派发 event 任务

    }
}
