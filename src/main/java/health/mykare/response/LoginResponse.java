package health.mykare.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;

    private long id;

    private String fullName;

}