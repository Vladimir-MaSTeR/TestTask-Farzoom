package Vladimir.The.image.service.controller;

import Vladimir.The.image.service.api.AllAlbumsResponse;
import Vladimir.The.image.service.api.ImageToAlbumResponse;
import Vladimir.The.image.service.api.ResultTrueResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService service;

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestParam String name) {
        return service.addAlbum(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> renameAlbum(@PathVariable int id, @RequestParam String name) {
        return service.renameAlbum(id, name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultTrueResponse> deleteAlbum(@PathVariable int id) {
        return service.deleteAlbum(id);
    }

    @GetMapping
    public ResponseEntity<AllAlbumsResponse> getAllAlbums() {
        return service.getAllAlbums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageToAlbumResponse> getImageToAlbum(@PathVariable int id) {
        return service.getImageToAlbum(id);
    }

}
