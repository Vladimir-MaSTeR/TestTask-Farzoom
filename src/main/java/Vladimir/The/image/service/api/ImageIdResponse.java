package Vladimir.The.image.service.api;

public class ImageIdResponse implements CommonResponse {

    private int id;



    public ImageIdResponse() {
    }

    public ImageIdResponse(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
