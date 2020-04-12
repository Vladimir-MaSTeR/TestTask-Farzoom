package Vladimir.The.image.service.controller;

import Vladimir.The.image.service.api.CommonResponse;
import Vladimir.The.image.service.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse addAlbum(@RequestParam String name) {
        return service.addAlbum(name);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse renameAlbum(@PathVariable int id, @RequestParam String name) {
        return service.renameAlbum(id, name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteAlbum(@PathVariable int id) {
        return service.deleteAlbum(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getAllAlbums() {
        return service.getAllAlbums();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse getImageToAlbum(@PathVariable int id) {
        return service.getImageToAlbum(id);
    }

}
