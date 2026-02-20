package in.rajatpandey.musifyapp.repository;

import in.rajatpandey.musifyapp.documents.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
