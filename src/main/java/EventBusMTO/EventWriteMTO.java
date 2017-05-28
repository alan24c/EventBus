package EventBusMTO;

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
}
