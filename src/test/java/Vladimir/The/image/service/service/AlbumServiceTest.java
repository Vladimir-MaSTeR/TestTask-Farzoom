package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.AllAlbumsResponse;
import Vladimir.The.image.service.api.ImageToAlbumResponse;
import Vladimir.The.image.service.api.NameAndIdAlbumResponse;
import Vladimir.The.image.service.api.ResultTrueResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.repository.AlbumRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

    private Album album;
    private Album album2;
    private Album album3;
    private List<NameAndIdAlbumResponse> nameAndIdAlbumResponseList;

    @MockBean
    private AlbumService albumService;

    @MockBean
    private AlbumRepository albumRepository;

    @Before
    public void setUp() {
        album = new Album("Хороший");
        album2 = new Album("Плохой");
        album3 = new Album("Новый");
        nameAndIdAlbumResponseList = new ArrayList<>();

        nameAndIdAlbumResponseList.add(new NameAndIdAlbumResponse(album.getAlbumId(), album.getAlbumName()));
        nameAndIdAlbumResponseList.add(new NameAndIdAlbumResponse(album2.getAlbumId(), album2.getAlbumName()));
        nameAndIdAlbumResponseList.add(new NameAndIdAlbumResponse(album3.getAlbumId(), album3.getAlbumName()));

    }

    @Test
    public void addAlbumTest() {
        Mockito.when(albumRepository.save(any(Album.class))).thenReturn(album);
        when(albumService.addAlbum(album.getAlbumName())).thenReturn(new ResponseEntity<>(album, HttpStatus.OK));

        albumService.addAlbum(album.getAlbumName());
        verify(albumService, times(1)).addAlbum(album.getAlbumName());

    }

    @Test
    public void renameAlbum() {
        Mockito.when(albumRepository.save(any(Album.class))).thenReturn(album);
        when(albumService.renameAlbum(album.getAlbumId(), "New name")).thenReturn(new ResponseEntity<>(album, HttpStatus.OK));

        albumService.renameAlbum(album.getAlbumId(), "New name");
        verify(albumService, times(1)).renameAlbum(album.getAlbumId(), "New name");
    }

    @Test
    public void deleteAlbum() {
        when(albumService.deleteAlbum(album.getAlbumId())).thenReturn(
                new ResponseEntity<>(new ResultTrueResponse(true), HttpStatus.OK));

        albumService.deleteAlbum(album.getAlbumId());
        verify(albumService, times(1)).deleteAlbum(album.getAlbumId());
    }

    @Test
    public void getAllAlbums() {
        when(albumService.getAllAlbums()).thenReturn(new ResponseEntity<>(new AllAlbumsResponse(nameAndIdAlbumResponseList), HttpStatus.OK));

        albumService.getAllAlbums();
        verify(albumService, times(1)).getAllAlbums();
    }

    @Test
    public void getImageToAlbum() {
        when(albumService.getImageToAlbum(album.getAlbumId())).
                thenReturn(new ResponseEntity<>(new ImageToAlbumResponse(album.getAlbumId(), album.getAlbumName(), album.getImageList()), HttpStatus.OK));

        albumService.getImageToAlbum(album.getAlbumId());
        verify(albumService, times(1)).getImageToAlbum(album.getAlbumId());
    }

}