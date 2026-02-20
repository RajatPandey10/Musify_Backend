package in.rajatpandey.musifyapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import in.rajatpandey.musifyapp.Service.SongService;
import in.rajatpandey.musifyapp.dto.SongRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<?> addSong(@RequestPart("request") String request ,
                                     @RequestPart("audio") MultipartFile audioFile,
                                     @RequestPart("image") MultipartFile imageFile){
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            SongRequest songRequest = objectMapper.readValue(request, SongRequest.class);
            songRequest.setImageFile(imageFile);
            songRequest.setAudioFile(audioFile);

            return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(songRequest));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
