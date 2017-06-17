package EventBusModel;

/**
 * Created by alan on 17-5-28.
 */
public class EventSubscribe {

    private String topic;

    private String consumerBeanName;

    private Integer index;

    public EventSubscribe(String consumerBeanName){
        this.consumerBeanName = consumerBeanName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getConsumerBeanName() {
        return consumerBeanName;
    }

    public void setConsumerBeanName(String consumerBeanName) {
        this.consumerBeanName = consumerBeanName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
