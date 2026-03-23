package in.rajatpandey.musifyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String email;

    private Role role;

    private String subscriptionPlan;

    public enum Role{
        USER,ADMIN
    }
}
