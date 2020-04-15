package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.AlbumIdResponse;
import Vladimir.The.image.service.api.AllAlbumsResponse;
import Vladimir.The.image.service.api.ImageToAlbumResponse;
import Vladimir.The.image.service.api.TrueFalseAndObjectResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.repository.AlbumRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

    private Album album;

    @MockBean
    private AlbumService albumService;

    @MockBean
    private AlbumRepository albumRepository;

    @Before
    public void setUp() {
        album = new Album("Хороший");
    }

    @Test
    public void addAlbumTest() {
        Mockito.when(albumRepository.save(any(Album.class))).thenReturn(album);
        doReturn(new TrueFalseAndObjectResponse()).when(albumService).addAlbum(album.getAlbumName());
        // тест ничего не делает, не проверяет
    }

    @Test
    public void renameAlbum() {
        Mockito.when(albumRepository.save(any(Album.class))).thenReturn(album);
        doReturn(new TrueFalseAndObjectResponse()).when(albumService).renameAlbum(album.getAlbumId(), "New name");
        // тест ничего не делает, не проверяет
    }

    @Test
    public void deleteAlbum() {
        doReturn(new TrueFalseAndObjectResponse()).when(albumService).deleteAlbum(album.getAlbumId());
        // тест ничего не делает, не проверяет
    }

    @Test
    public void getAllAlbums() {
        doReturn(new AllAlbumsResponse()).when(albumService).getAllAlbums();
        // тест ничего не делает, не проверяет
    }

    @Test
    public void getImageToAlbum() {
        doReturn(new ImageToAlbumResponse()).when(albumService).getImageToAlbum(album.getAlbumId());
        // тест ничего не делает, не проверяет
    }

}