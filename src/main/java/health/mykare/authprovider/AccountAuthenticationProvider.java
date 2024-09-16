package health.mykare.authprovider;


import health.mykare.authtoken.MemberUsernameAndPasswordAuthToken;
import health.mykare.filter.UserDetailsImpl;
import health.mykare.filter.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl accountDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws ApplicationContextException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        final UserDetailsImpl userDetails = this.accountDetailsService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ApplicationContextException("Either username or password wrong");
        }
        return new MemberUsernameAndPasswordAuthToken(userDetails, userDetails.getPassword(), Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(MemberUsernameAndPasswordAuthToken.class);
    }
}
