package Vladimir.The.image.service.api;

public class TrueFalseAndObjectResponse implements CommonResponse {

    private boolean result;
    private Object message;



    public TrueFalseAndObjectResponse() {
    }

    public TrueFalseAndObjectResponse(boolean result) {
        this.result = result;
    }

    public TrueFalseAndObjectResponse(boolean result, Object message) {
        this.result = result;
        this.message = message;
    }




    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }
}
