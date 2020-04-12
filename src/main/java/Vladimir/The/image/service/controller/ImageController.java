package Vladimir.The.image.service.controller;


import Vladimir.The.image.service.api.CommonResponse;
import Vladimir.The.image.service.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/album/{id}/image")
public class ImageController {

    @Autowired
    ImageService service;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse addImageToAlbum(@PathVariable("id") int albumId, @RequestParam MultipartFile file) {
        try {
            return service.addImageToAlbum(albumId, file);
        } catch (IOException e) {
            return (CommonResponse) new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse deleteImage(@PathVariable("id") int albumId, @PathVariable("imageId") int imageId) {
        return service.deleteImage(albumId, imageId);
    }

    @GetMapping(path = "/{imageId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity copyImage(@PathVariable("id") int albumId, @PathVariable("imageId") int imageId) {
            return service.copyImage(albumId, imageId);
    }

}
