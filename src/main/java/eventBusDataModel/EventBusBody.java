package eventBusDataModel;

/**
 * Created by alan on 17-5-6.
 */
public class EventBusBody {

    private String topic;

    private String context;

    private int consumerNums;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getConsumerNums() {
        return consumerNums;
    }

    public void setConsumerNums(int consumerNums) {
        this.consumerNums = consumerNums;
    }
}
