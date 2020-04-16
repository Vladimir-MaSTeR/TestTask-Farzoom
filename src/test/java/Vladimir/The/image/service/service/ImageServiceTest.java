package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.AddImageResponse;
import Vladimir.The.image.service.api.CopyImageResponse;
import Vladimir.The.image.service.api.ResultTrueResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @MockBean
    private ImageService imageService;
    @MockBean
    private MultipartFile multipartFile;
    @MockBean
    private Resource resource;

    private Album album;
    private Image image;

    @Before
    public void setUp() {
        album = new Album();
        image = new Image();
    }


    @Test
    public void addImageToAlbumTest() {
        when(imageService.addImageToAlbum(album.getAlbumId(), multipartFile)).
                thenReturn(new ResponseEntity<>(new AddImageResponse(album.getAlbumId(), image), HttpStatus.OK));

        imageService.addImageToAlbum(album.getAlbumId(), multipartFile);
        verify(imageService, times(1)).addImageToAlbum(album.getAlbumId(), multipartFile);
    }

    @Test
    public void deleteImageTest() {
        when(imageService.deleteImage(album.getAlbumId(), image.getImageId())).
                thenReturn(new ResponseEntity<>(new ResultTrueResponse(true), HttpStatus.OK));

        imageService.deleteImage(album.getAlbumId(), image.getImageId());
        verify(imageService, times(1)).deleteImage(album.getAlbumId(), image.getImageId());
    }

    @Test
    public void copyImageTest() {
        when(imageService.copyImage(album.getAlbumId(), image.getImageId())).thenReturn(new CopyImageResponse(image.getImageName(), resource));

        imageService.copyImage(album.getAlbumId(), image.getImageId());
        verify(imageService, times(1)).copyImage(album.getAlbumId(), image.getImageId());
    }
}