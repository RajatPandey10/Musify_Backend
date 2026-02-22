package in.rajatpandey.musifyapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import in.rajatpandey.musifyapp.Service.SongService;
import in.rajatpandey.musifyapp.dto.SongListResponse;
import in.rajatpandey.musifyapp.dto.SongRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<?> listSongs(){
        try{
            return ResponseEntity.ok(songService.getAllSongs());
        }catch (Exception e){
            return ResponseEntity.ok(new SongListResponse(false,null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable String id){
        try{
            boolean removed = songService.removeSong(id);
            if(removed){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
