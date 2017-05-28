package EventBusModel;

/**
 * Created by alan on 17-5-28.
 */
public class EventTopic {

    private String topicName;

    public EventTopic(String topicName){
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
