package EventBusModel;

/**
 * Created by alan on 17-5-28.
 */
// db 中的数据模型
public class Event {

    // 事件主题
    private String topic;

    // 消费上下文
    private String context;

    // 消费是否成功
    private boolean isSuccess;

    // 消费次数
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

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getConsumerNums() {
        return consumerNums;
    }

    public void setConsumerNums(int consumerNums) {
        this.consumerNums = consumerNums;
    }
}