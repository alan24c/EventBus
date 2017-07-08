package EventBusModel;

import EventBusMTO.EventUpdateMTO;

/**
 * Created by alan on 17-5-28.
 */
// 内存中Event的数据模型
public class Event {

    // db主键ID
    // TODO: 17-7-8 以后改为 sequence 自动生成,不污染内存 model
    private int id;

    // 事件主题
    private String topic;

    // 消费上下文(事件携带的参数)
    private String context;

    // 消费是否成功
    private boolean isSuccess;

    // 消费次数(用于做性能统计)
    private int consumerNums;

    /* 事件的处理掩码
       每一个 bit 位表示对应的 processor 处理是否成功
       0 表示处理成功,1表示处理失败
    */
    private int consumerMask;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getConsumerMask() {
        return consumerMask;
    }

    public void setConsumerMask(int consumerMask) {
        this.consumerMask = consumerMask;
    }
}
