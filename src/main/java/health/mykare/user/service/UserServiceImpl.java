package health.mykare.user.service;

import health.mykare.dto.LoginUserDTO;
import health.mykare.execeptions.MyKareHealthApplicationException;
import health.mykare.response.LoginResponse;
import health.mykare.user.entity.User;
import health.mykare.user.entity.UserRepository;
import health.mykare.user.request.CreateUserRequest;
import health.mykare.utils.AccessTokenUtils;
import health.mykare.utils.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final AuthenticationService authenticationService;

    private final AccessTokenUtils accessTokenUtils;

    @Override
    @Transactional
    public Response registerUser(CreateUserRequest createUserRequest) {
        try {
            this.validateUserRequest(createUserRequest);
            Optional<User> oldUser = this.userRepository.fetchUserByEmail(createUserRequest.getEmail());
            if (oldUser.isPresent()) {
                return new Response("User with this email already exists");
            }
            User user = new User();
            user.setName(createUserRequest.getName());
            user.setEmail(createUserRequest.getEmail());
            user.setGender(createUserRequest.getGender());
            user.setPassword(createUserRequest.getPassword());
            this.userRepository.saveAndFlush(user);
            return new Response("User Register successfully");
        } catch (Exception e) {
            throw new MyKareHealthApplicationException(e.getMessage());
        }

    }

    @Override
    public LoginResponse validateUser(String email, String password) {
        try {
            if(StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
                throw new MyKareHealthApplicationException("Required fields are missing");
            }
            Optional<User> user = this.userRepository.fetchUserByEmailAndPassword(email, password);
            if (user.isEmpty()) {
                throw new MyKareHealthApplicationException("Invalid email or password");
            }
            LoginUserDTO loginUserDTO = new LoginUserDTO();
            loginUserDTO.setEmail(email);
            loginUserDTO.setPassword(password);
            String jwtToken = accessTokenUtils.generateAccessToken((user.get().getId()));
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(user.get().getId());
            loginResponse.setFullName(user.get().getName());
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            return loginResponse;
        } catch (Exception e) {
            throw new MyKareHealthApplicationException(e.getMessage());
        }

    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        try {
            Optional<User> user = this.userRepository.findById(id);
            if (user.isEmpty()) {
                throw new MyKareHealthApplicationException("user not found");
            }
            this.userRepository.delete(user.get());
        } catch (Exception e) {
            throw new MyKareHealthApplicationException(e.getMessage());
        }
    }

    public void validateUserRequest(CreateUserRequest createUserRequest) {
        if (StringUtils.isBlank(createUserRequest.getName())) {
            throw new MyKareHealthApplicationException("name must not be empty");
        }
        if (StringUtils.isBlank(createUserRequest.getEmail())) {
            throw new MyKareHealthApplicationException("Email must not be empty");
        }

        if (StringUtils.isBlank(createUserRequest.getGender())) {
            throw new MyKareHealthApplicationException("Gender must not be empty");
        }

        if (StringUtils.isBlank(createUserRequest.getPassword())) {
            throw new MyKareHealthApplicationException("Password must not be empty");
        }
    }

}
