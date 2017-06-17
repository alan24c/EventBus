package EventBusModel;

/**
 * Created by alan on 17-5-28.
 */
/*
    订阅者的核心是 consumerBean,
    其所属的 topic 应该是在 relationShip中呈现,而不是固化在该 bean 中
 */
public class EventSubscribe {

    private String consumerBeanName;

    private int index;

    public EventSubscribe(String consumerBeanName,int index){
        this.consumerBeanName = consumerBeanName;
        this.index = index;
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
