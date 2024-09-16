package health.mykare.security;


import health.mykare.user.data.AppUser;

public interface PlatformSecurityContext {

    AppUser authenticateUser();
}
