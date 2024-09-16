package health.mykare.execeptions;

public class AuthenticationException extends AbstractPlatformException {

    public AuthenticationException(String message) {
        super("error.msg.invalid.credentials", message);
    }
}
