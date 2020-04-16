package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.AddImageResponse;
import Vladimir.The.image.service.api.CopyImageResponse;
import Vladimir.The.image.service.api.ResultTrueResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.model.Image;
import Vladimir.The.image.service.repository.AlbumRepository;
import Vladimir.The.image.service.repository.ImageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private AlbumService albumService;

    private static Logger logger = LogManager.getLogger(ImageService.class.getName());


    public ResponseEntity<AddImageResponse> addImageToAlbum(int albumId, MultipartFile multipartFile) {
        Album album = albumRepository.findByAlbumId(albumId);

        if (album == null || multipartFile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String imageName = generateStringImageName();
        String directoryPath = albumService.getUploadPath() + album.getAlbumName();
        Path path = Paths.get(directoryPath, imageName);

        try {
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error(e);
        }

        Image image = new Image(album, imageName, directoryPath);
        imageRepository.save(image);

        return new ResponseEntity<>(new AddImageResponse(albumId, image), HttpStatus.OK);
    }

    public ResponseEntity<ResultTrueResponse> deleteImage(int albumId, long imageId) {
        Album album = albumRepository.findByAlbumId(albumId);
        Image image = imageRepository.findByImageId(imageId);

        if (album == null || image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File file = new File(image.getPathImage() + "/" + image.getImageName());
        file.delete();

        imageRepository.delete(image);

        return new ResponseEntity<>(new ResultTrueResponse(true), HttpStatus.OK);
    }

    public Object copyImage(int albumId, long imageId) {
        Album album = albumRepository.findByAlbumId(albumId);
        Image image = imageRepository.findByImageId(imageId);

        if (album == null || image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Path path = Paths.get(image.getPathImage() + "/" + image.getImageName());
        Resource resource = null;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error(e);
        }

        if (resource == null || !resource.exists() || !resource.isReadable()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new CopyImageResponse(image.getImageName(), resource);

//
    }


    private String generateStringImageName() {
        return UUID.randomUUID().toString() + ".jpg";
    }

}
