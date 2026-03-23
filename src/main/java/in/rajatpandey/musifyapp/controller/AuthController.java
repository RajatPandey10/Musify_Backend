package in.rajatpandey.musifyapp.controller;

import in.rajatpandey.musifyapp.Service.AppUserDetailsService;
import in.rajatpandey.musifyapp.Service.UserService;
import in.rajatpandey.musifyapp.documents.User;
import in.rajatpandey.musifyapp.dto.AuthRequest;
import in.rajatpandey.musifyapp.dto.AuthResponse;
import in.rajatpandey.musifyapp.dto.RegisterRequest;
import in.rajatpandey.musifyapp.dto.UserResponse;
import in.rajatpandey.musifyapp.repository.UserRepository;
import in.rajatpandey.musifyapp.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{

            User existingUser = userService.findByEmail(request.getEmail());
            if(request.getPortal().equalsIgnoreCase("admin") &&
                    existingUser.getRole().name().equalsIgnoreCase("USER")){
                return ResponseEntity.badRequest().body("Email/Password is incorrect");

            }
//            Authenticate the user
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

//           Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());


//            Generate jwt token
            String token = jwtUtil.generateToken(userDetails,existingUser.getRole().name());

           return ResponseEntity.ok(new AuthResponse(token,request.getEmail(),existingUser.getRole().name(), existingUser.getSubscriptionPlan()));
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Email/Password is incorrect");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        try{
            UserResponse response = userService.registerUser(request);
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/promote-admin")
    public ResponseEntity<?> promoteToAdmin(@RequestBody Map<String,String> request){
        try{
           User user = userService.promoteToAdmin(request.get("email"));
           return ResponseEntity.ok(new AuthResponse(null,user.getEmail(),"ADMIN","Premium"));
        }catch (Exception e){
            return  ResponseEntity.badRequest().body("Failed to promote user to admin");
        }
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<?> getProfile(@PathVariable String email) {
        try {
            UserResponse existingUser = userService.getProfile(email);
            return ResponseEntity.ok(existingUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No user found");
        }
    }
}
