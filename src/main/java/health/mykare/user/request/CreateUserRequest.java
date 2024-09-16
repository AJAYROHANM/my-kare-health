package health.mykare.user.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;

    private String email;

    private String gender;

    private String password;

}
