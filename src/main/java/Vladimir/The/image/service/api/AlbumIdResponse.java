package Vladimir.The.image.service.api;

public class AlbumIdResponse implements CommonResponse {

    private int albumId;


    public AlbumIdResponse() {
    }

    public AlbumIdResponse(int albumId) {
        this.albumId = albumId;
    }



    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
