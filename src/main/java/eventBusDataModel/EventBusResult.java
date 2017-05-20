package eventBusDataModel;

/**
 * Created by alan on 17-5-6.
 */
public class EventBusResult {

    private boolean isSuccess;

    private String errorMessage;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
