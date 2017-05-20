package Domain;

/**
 * Created by alan on 17-5-20.
 */
public class Event {

    private Integer id;

    private String topic;

    private String processor;

    private String context;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
