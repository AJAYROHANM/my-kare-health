package health.mykare.authtoken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MemberUsernameAndPasswordAuthToken extends UsernamePasswordAuthenticationToken {

    public MemberUsernameAndPasswordAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public MemberUsernameAndPasswordAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }


}
