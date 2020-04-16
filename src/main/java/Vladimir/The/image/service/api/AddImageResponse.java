package Vladimir.The.image.service.api;

import Vladimir.The.image.service.model.Image;

public class AddImageResponse {

    private int albumId;
    private Image image;
//    private long imageId;
//    private String imageName;
//    private String pathImage;



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

    //    public AddImageResponse(int albumId, long imageId, String imageName, String pathImage) {
//        this.albumId = albumId;
//        this.imageId = imageId;
//        this.imageName = imageName;
//        this.pathImage = pathImage;
//    }
//
//    public int getAlbumId() {
//        return albumId;
//    }
//
//    public void setAlbumId(int albumId) {
//        this.albumId = albumId;
//    }
//
//    public long getImageId() {
//        return imageId;
//    }
//
//    public void setImageId(long imageId) {
//        this.imageId = imageId;
//    }
//
//    public String getImageName() {
//        return imageName;
//    }
//
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//    public String getPathImage() {
//        return pathImage;
//    }
//
//    public void setPathImage(String pathImage) {
//        this.pathImage = pathImage;
//    }
}
