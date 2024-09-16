package health.mykare.user.api;

import health.mykare.execeptions.MyKareHealthApplicationException;
import health.mykare.response.LoginResponse;
import health.mykare.security.SpringSecurityPlatformSecurityContext;
import health.mykare.user.data.AppUser;
import health.mykare.user.entity.User;
import health.mykare.user.entity.UserRepository;
import health.mykare.user.request.CreateUserRequest;
import health.mykare.user.service.UserService;
import health.mykare.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiResource {

    private final UserService userService;

    private final UserRepository userRepository;

    private final SpringSecurityPlatformSecurityContext springSecurityPlatformSecurityContext;


    @PostMapping("/register")
    public Response registerUser(@RequestBody CreateUserRequest createUserRequest) {
            return this.userService.registerUser(createUserRequest);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        return userService.validateUser(email, password);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        try {
            this.springSecurityPlatformSecurityContext.authenticateUser();
            return this.userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();
        Optional<User> user = this.userRepository.fetchUserByEmail(email);
        if (user.isPresent()) {
            if (user.get().getEmail().equals(authenticatedUserEmail)) {
                return ResponseEntity.badRequest().body("You cannot delete yourself");
            }
            this.userService.deleteUser(user.get().getId());
            return ResponseEntity.ok("User deleted successfully");
        }

        return ResponseEntity.ok("User not found");
    }
}
