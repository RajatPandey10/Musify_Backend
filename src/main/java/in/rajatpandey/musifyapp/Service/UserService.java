package in.rajatpandey.musifyapp.Service;

import in.rajatpandey.musifyapp.documents.User;
import in.rajatpandey.musifyapp.dto.RegisterRequest;
import in.rajatpandey.musifyapp.dto.UserResponse;
import in.rajatpandey.musifyapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User toDocument(RegisterRequest request){
        return User.builder()
                .subscriptionPlan("Basic")
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.USER)
                .build();
    }

    public UserResponse toResponse(User newUser){
        return UserResponse.builder()
                .subscriptionPlan(newUser.getSubscriptionPlan())
                .id(newUser.getId())
                .email(newUser.getEmail())
                .role(UserResponse.Role.USER)
                .build();

    }

    public UserResponse registerUser(RegisterRequest request){

//        chack if email already exists
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
//        create new user
        User newUser = User.builder()
                .subscriptionPlan("Basic")
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.USER)
                .build();

        userRepository.save(newUser);

        return UserResponse.builder()
                .subscriptionPlan(newUser.getSubscriptionPlan())
                .id(newUser.getId())
                .email(newUser.getEmail())
                .role(UserResponse.Role.USER)
                .build();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found for the email"+email));
    }

    public  User promoteToAdmin(String email){
        User existingUser = findByEmail(email);
        existingUser.setRole(User.Role.ADMIN);
        return userRepository.save(existingUser);
    }

    public UserResponse getProfile(String email){
        User existingUser = findByEmail(email);

        return toResponse(existingUser);
    }
}
