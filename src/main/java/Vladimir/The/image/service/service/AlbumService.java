package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.*;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;


    public TrueFalseAndObjectResponse addAlbum(String name) {
        Album searchAlbumName = albumRepository.searchAlbumName(name).stream().findFirst().orElse(null);

        if (name.length() == 0 || searchAlbumName != null) {
            return (TrueFalseAndObjectResponse) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        File dir = new File(relativePath(name));
        Album album = new Album(name);
        albumRepository.save(album);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return new TrueFalseAndObjectResponse(true, new AlbumIdResponse(album.getAlbumId()));
    }

    public TrueFalseAndObjectResponse renameAlbum(int albumId, String name) {
        Album album = albumRepository.albumId(albumId).stream().findFirst().orElse(null);

        if (album == null || name.length() == 0) {
            return (TrueFalseAndObjectResponse) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        File dir = new File(relativePath(album.getAlbumName()));
        File newDir = new File(relativePath(name));

        if (dir.exists()) {
            dir.renameTo(newDir);
        }

        album.setAlbumName(name);
        albumRepository.save(album);

        return new TrueFalseAndObjectResponse(true);
    }

    public TrueFalseAndObjectResponse deleteAlbum(int albumId) {
        Album album = albumRepository.albumId(albumId).stream().findFirst().orElse(null);

        if (album == null) {
            return (TrueFalseAndObjectResponse) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        File dir = new File(relativePath(album.getAlbumName()));
        deleteDirectory(dir);

        albumRepository.delete(album);

        return new TrueFalseAndObjectResponse(true);
    }

    public AllAlbumsResponse getAllAlbums() {
        List<Album> albumsList = albumRepository.findAll();
        List<AllAlbums> allAlbumsList = new ArrayList<>();

        if (albumsList.size() == 0) {
            return (AllAlbumsResponse) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        for (Album album : albumsList) {
            allAlbumsList.add(new AllAlbums(album.getAlbumId(), album.getAlbumName()));
        }

        return new AllAlbumsResponse(allAlbumsList);
    }

    public ImageToAlbumResponse getImageToAlbum(int albumId) {
        Album album = albumRepository.albumId(albumId).stream().findFirst().orElse(null);

        if (album == null) {
            return (ImageToAlbumResponse) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        return new ImageToAlbumResponse(album.getImageList());
    }


    private String relativePath(String name) {
        return "src/main/resources/albums/" + name;
    }

    private void deleteDirectory(File path) {
        if (path.isDirectory()) {

            for (File f : Objects.requireNonNull(path.listFiles())) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else f.delete();
            }
        }
        path.delete();
    }


}
