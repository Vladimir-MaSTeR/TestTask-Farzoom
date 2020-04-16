package Vladimir.The.image.service.controller;


import Vladimir.The.image.service.api.AddImageResponse;
import Vladimir.The.image.service.api.CopyImageResponse;
import Vladimir.The.image.service.api.ResultTrueResponse;
import Vladimir.The.image.service.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/album/{id}/image")
public class ImageController {

        @Autowired
    ImageService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AddImageResponse> addImageToAlbum(@PathVariable("id") int albumId, @RequestPart MultipartFile file) {
            return service.addImageToAlbum(albumId, file);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<ResultTrueResponse> deleteImage(@PathVariable("id") int albumId, @PathVariable("imageId") long imageId) {
        return service.deleteImage(albumId, imageId);
    }

    @GetMapping(path = "/{imageId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> copyImage(@PathVariable("id") int albumId, @PathVariable("imageId") int imageId) {

       CopyImageResponse copyImageResponse = (CopyImageResponse) service.copyImage(albumId, imageId);

        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + copyImageResponse.getImageName())
                .body(copyImageResponse.getResource());

    }

}
