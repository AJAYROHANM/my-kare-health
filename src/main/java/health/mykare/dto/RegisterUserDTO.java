package health.mykare.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {

    private String email;

    private String password;

    private String fullName;

}