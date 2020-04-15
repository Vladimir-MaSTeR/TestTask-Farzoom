package Vladimir.The.image.service.service;

import Vladimir.The.image.service.api.ImageIdResponse;
import Vladimir.The.image.service.api.TrueFalseAndObjectResponse;
import Vladimir.The.image.service.model.Album;
import Vladimir.The.image.service.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

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
        try {
            doReturn(new ImageIdResponse()).when(imageService).addImageToAlbum(album.getAlbumId(), multipartFile);
            // тест ничего не делает, не проверяет
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteImageTest() {
        doReturn(new TrueFalseAndObjectResponse()).when(imageService).deleteImage(album.getAlbumId(), image.getImageId());
        // тест ничего не делает, не проверяет
    }

    @Test
    public void copyImageTest() {
        doReturn(ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + image.getImageName())
                .body(resource))
                .when(imageService).copyImage(album.getAlbumId(), image.getImageId());
        // тест ничего не делает, не проверяет
    }
}