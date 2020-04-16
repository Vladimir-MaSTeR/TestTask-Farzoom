package Vladimir.The.image.service.api;

import Vladimir.The.image.service.model.Image;

import java.util.List;

public class ImageToAlbumResponse {

    private int albumId;
    private String albumName;
    private List<Image> images;


    public ImageToAlbumResponse() {
    }

    public ImageToAlbumResponse(int albumId, String albumName, List<Image> images) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.images = images;
    }


    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
}
