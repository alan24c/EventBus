package EventBusMTO;

import java.io.Serializable;

/**
 * Created by alan on 17-5-28.
 */
public class EventReadMTO {

    private String topicName;

    private int nums;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public void validate(){
        if( null == topicName){

        }
    }
}
