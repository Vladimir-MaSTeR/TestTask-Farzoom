package Vladimir.The.image.service.repository;

import Vladimir.The.image.service.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query(value = "SELECT * FROM albums a WHERE a.album_name = ?1", nativeQuery = true)
    List<Album> searchAlbumName(String name);

    @Query(value = "SELECT * FROM albums a WHERE a.album_id = ?1", nativeQuery = true)
    List<Album> albumId(int id);
}
