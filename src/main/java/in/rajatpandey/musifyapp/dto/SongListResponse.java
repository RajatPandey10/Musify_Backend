package in.rajatpandey.musifyapp.dto;


import in.rajatpandey.musifyapp.documents.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongListResponse {

    private boolean success;
    private List<Song> songs;
}
