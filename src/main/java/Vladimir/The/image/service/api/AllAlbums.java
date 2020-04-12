package Vladimir.The.image.service.api;

public class AllAlbums implements CommonResponse {

    private int id;
    private String name;


    public AllAlbums() {
    }

    public AllAlbums(int id, String name) {
        this.id = id;
        this.name = name;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}