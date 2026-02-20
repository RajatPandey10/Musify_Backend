package in.rajatpandey.musifyapp.repository;

import in.rajatpandey.musifyapp.documents.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song,String> {
}
