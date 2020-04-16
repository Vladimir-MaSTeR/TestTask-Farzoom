package Vladimir.The.image.service.api;

import Vladimir.The.image.service.model.Image;

public class AddImageResponse {

    private int albumId;
    private Image image;


    public AddImageResponse() {
    }

    public AddImageResponse(int albumId, Image image) {
        this.albumId = albumId;
        this.image = image;
    }

    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
