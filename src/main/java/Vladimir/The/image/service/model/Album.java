package Vladimir.The.image.service.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_id")
    private int albumId;

    @Column(name = "album_name", nullable = false)
    private String albumName;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Image> imageList;


    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName;

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

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

}
