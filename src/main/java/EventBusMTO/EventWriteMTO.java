package EventBusMTO;

import EventBusModel.Event;
import utils.FastJsonUtils;

import java.io.Serializable;

/**
 * Created by alan on 17-5-28.
 */
public class EventWriteMTO {

    public String topic;

    public Serializable context;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Serializable getContext() {
        return context;
    }

    public void setContext(Serializable context) {
        this.context = context;
    }

    public void validate(){
        if(null == topic){

        }

        if(context == null){

        }
    }

    public Event toEvent(){
        Event event = new Event();

        event.setTopic(topic);
        event.setContext(FastJsonUtils.objectToString(event.getContext()));
        event.setSuccess(false);
        event.setConsumerNums(0);

        return event;
    }
}
