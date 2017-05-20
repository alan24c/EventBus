package eventBusProcessor;

import eventBusDataModel.EventBusContext;
import eventBusDataModel.EventBusResult;

/**
 * Created by alan on 17-5-6.
 */
/*
    EventBus 调度任务的处理器
    具体实现逻辑由使用方来定义
 */
public interface EventBusProcessor {

    // 子类实现该方法实现任务的处理
    public EventBusResult process(EventBusContext context);
}