package Vladimir.The.image.service.api;

import java.util.List;

public class AllAlbumsResponse {


    private List<NameAndIdAlbumResponse> albums;


    public AllAlbumsResponse() {
    }

    public AllAlbumsResponse(List<NameAndIdAlbumResponse> albums) {
        this.albums = albums;
    }



    public List<NameAndIdAlbumResponse> getAlbums() {
        return albums;
    }
    public void setAlbums(List<NameAndIdAlbumResponse> albums) {
        this.albums = albums;
    }
}
