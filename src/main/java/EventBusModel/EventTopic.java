package EventBusModel;

/**
 * Created by alan on 17-5-28.
 */
public class EventTopic {

    private String topicName;

    private Integer maskNums;

    public EventTopic(String topicName,int maskNums){
        this.topicName = topicName;
        this.maskNums = maskNums;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getMaskNums() {
        return maskNums;
    }

    public void setMaskNums(Integer maskNums) {
        this.maskNums = maskNums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventTopic that = (EventTopic) o;

        if (!topicName.equals(that.topicName)) return false;
        return maskNums.equals(that.maskNums);
    }

    @Override
    public int hashCode() {
        int result = topicName.hashCode();
        result = 31 * result + maskNums.hashCode();
        return result;
    }
}
