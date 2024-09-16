package health.mykare.user.service;

import health.mykare.dto.LoginUserDTO;
import health.mykare.response.LoginResponse;
import health.mykare.user.entity.User;
import health.mykare.user.request.CreateUserRequest;
import health.mykare.utils.Response;

import java.util.Optional;

public interface UserService {

    Response registerUser(CreateUserRequest createUserRequest);

    LoginResponse validateUser(String email, String password);

    void deleteUser(Long id);
}
