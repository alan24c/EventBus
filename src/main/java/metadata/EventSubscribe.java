package metadata;

import eventBusProcessor.EventBusProcessor;

/**
 * Created by alan on 17-5-14.
 */
public class EventSubscribe {

    private String topic;

    private String consumerProcessor;

    private String describe;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getConsumerProcessor() {
        return consumerProcessor;
    }

    public void setConsumerProcessor(String consumerProcessor) {
        this.consumerProcessor = consumerProcessor;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void validate(){


    }
}
