package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.ImageIdResponse;
import Vladimir.The.image.service.api.TrueFalseAndObjectResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.model.Image;
import Vladimir.The.image.service.repository.AlbumRepository;
import Vladimir.The.image.service.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AlbumRepository albumRepository;


    public ImageIdResponse addImageToAlbum(int albumId, MultipartFile multipartFile) throws IOException {
        Album album = albumRepository.albumId(albumId).stream().findFirst().orElse(null);

        if (album == null || multipartFile.isEmpty()) {
            return (ImageIdResponse) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        // объявление константы в методе
        final int LENGTH_NAME_GENERATE = 20;
        // неверная нотация для переменной
        final String NAME_IMAGE = generateStringImageName(LENGTH_NAME_GENERATE);
        // не уместный путь хранения файлов
        final String DIRECTORY_PATH = "src/main/resources/albums/" + album.getAlbumName();

        Path path = Paths.get(DIRECTORY_PATH, NAME_IMAGE);
        Path file = Files.createFile(path);

        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file.toString()))) {
            // полное вычитывание файла в память через .getBytes()
            stream.write(multipartFile.getBytes());
        }

        Image image = new Image(album, NAME_IMAGE, DIRECTORY_PATH);
        imageRepository.save(image);

        // нужно возвращать полное состояние сущности (или хотя бы еще идентификатор альбома)
        return new ImageIdResponse(image.getImageId());
    }

    public TrueFalseAndObjectResponse deleteImage(int albumId, int imageId) {
        Album album = albumRepository.albumId(albumId).stream().findFirst().orElse(null);
        Image image = imageRepository.searchImageId(imageId).stream().findFirst().orElse(null);

        if (album == null || image == null) {
            // неверный каст BodyBuilder -> TrueFalseAndObjectResponse
            return (TrueFalseAndObjectResponse) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        File file = new File(image.getPathImage() + "/" + image.getImageName());
        file.delete();

        imageRepository.delete(image);

        return new TrueFalseAndObjectResponse(true);
    }

    public ResponseEntity copyImage(int albumId, int imageId) {
        Album album = albumRepository.albumId(albumId).stream().findFirst().orElse(null);
        Image image = imageRepository.searchImageId(imageId).stream().findFirst().orElse(null);

        if (album == null || image == null) {
            // неверный каст BodyBuilder -> ResponseEntity
            return (ResponseEntity) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        Path path = Paths.get(image.getPathImage() + "/" + image.getImageName());
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            // неверное логирование ошибки
            e.printStackTrace();
        }

        // resource может быть null
        if (!resource.exists() || !resource.isReadable()) {
            // неверный каст BodyBuilder -> ResponseEntity
            return (ResponseEntity) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        // формирование заголовка http в сервисе
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + image.getImageName())
                .body(resource);
    }


    // не нужный алгоритм формирования уникальной строки, см UUID.randomUUID().toString()
    private String generateStringImageName(int numMs) {
        char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        char[] index = new char[numMs];
        Random r = new Random();
        int i = 0;

        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        // недостаточная абстракция алгоритма от конкретного применения
        return new String(index) + ".jpg";
    }

    // неиспользуемый метод
    private void deleteFile(File path) {
        for (File f : Objects.requireNonNull(path.listFiles())) {
            if (!f.isDirectory()) {
                f.delete();
            }
        }
    }


}
