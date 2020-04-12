package Vladimir.The.image.service.api;

import java.util.List;

public class AllAlbumsResponse implements CommonResponse {


    private List<AllAlbums> albums;


    public AllAlbumsResponse() {
    }

    public AllAlbumsResponse(List<AllAlbums> albums) {
        this.albums = albums;
    }



    public List<AllAlbums> getAlbums() {
        return albums;
    }
    public void setAlbums(List<AllAlbums> albums) {
        this.albums = albums;
    }
}
