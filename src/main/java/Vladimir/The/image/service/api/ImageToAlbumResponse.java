package Vladimir.The.image.service.api;

import Vladimir.The.image.service.model.Image;

import java.util.List;

public class ImageToAlbumResponse implements CommonResponse {

    private List<Image> images;



    public ImageToAlbumResponse() {
    }

    public ImageToAlbumResponse(List<Image> images) {
        this.images = images;
    }



    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
}
