package health.mykare.security;


import health.mykare.context.AppContext;
import health.mykare.execeptions.MyKareHealthApplicationException;
import health.mykare.execeptions.NotFoundException;
import health.mykare.user.data.AppUser;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityPlatformSecurityContext implements PlatformSecurityContext {

    @Override
    public AppUser authenticateUser() {
        final AppUser appUser = AppContext.get();
        if(appUser == null) {
            throw new MyKareHealthApplicationException("Authentication Failed");
        }
        return appUser;
    }
}
