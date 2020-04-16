package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.AllAlbumsResponse;
import Vladimir.The.image.service.api.ImageToAlbumResponse;
import Vladimir.The.image.service.api.NameAndIdAlbumResponse;
import Vladimir.The.image.service.api.ResultTrueResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AlbumService {


    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AlbumRepository albumRepository;


    public ResponseEntity<Album> addAlbum(String name) {
        Album searchAlbumName = albumRepository.findByAlbumName(name);

        if (name.length() == 0 || searchAlbumName != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File dir = new File(uploadPath + name);
        Album album = new Album(name);
        albumRepository.save(album);

        dir.mkdirs();

        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    public ResponseEntity<Album> renameAlbum(int albumId, String name) {
        Album album = albumRepository.findByAlbumId(albumId);

        if (album == null || name.length() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File dir = new File(uploadPath + album.getAlbumName());
        File newDir = new File(uploadPath + name);

        dir.renameTo(newDir);

        album.setAlbumName(name);
        albumRepository.save(album);

        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    public ResponseEntity<ResultTrueResponse> deleteAlbum(int albumId) {
        Album album = albumRepository.findByAlbumId(albumId);

        if (album == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File dir = new File(uploadPath + album.getAlbumName());
        deleteDirectory(dir);

        albumRepository.delete(album);

        return new ResponseEntity<>(new ResultTrueResponse(true), HttpStatus.OK);
    }

    public ResponseEntity<AllAlbumsResponse> getAllAlbums() {
        List<Album> albumsList = albumRepository.findAll();

        List<NameAndIdAlbumResponse> nameAndIdAlbumResponseList = albumsList.stream().
                map(album -> new NameAndIdAlbumResponse(album.getAlbumId(), album.getAlbumName())).
                collect(Collectors.toCollection(ArrayList::new));

        return new ResponseEntity<>(new AllAlbumsResponse(nameAndIdAlbumResponseList), HttpStatus.OK);
    }

    public ResponseEntity<ImageToAlbumResponse> getImageToAlbum(int albumId) {
        Album album = albumRepository.findByAlbumId(albumId);

        if (album == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ImageToAlbumResponse(albumId, album.getAlbumName(), album.getImageList()), HttpStatus.OK);
    }


    private void deleteDirectory(File path) {
        if (path.isDirectory()) {

            for (File f : (path.listFiles())) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else f.delete();
            }
        }
        path.delete();
    }


    public String getUploadPath() {
        return uploadPath;
    }

}
