package Vladimir.The.image.service.api;

public class TrueFalseAndObjectResponse implements CommonResponse {

    private boolean result;
    // не совсем понятен смысл именнования этого поля как "сообщение"
    private Object message;



    public TrueFalseAndObjectResponse() {
    }

    public TrueFalseAndObjectResponse(boolean result) {
        // если данный конструктор вызывается всегда с result = true, тогда в чем его смысл
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
