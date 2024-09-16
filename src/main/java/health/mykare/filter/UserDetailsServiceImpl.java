package health.mykare.filter;



import health.mykare.user.entity.User;
import health.mykare.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.fetchUserByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }
        return UserDetailsImpl.build(user.get());
    }
}
