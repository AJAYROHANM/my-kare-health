package health.mykare.execeptions;

public class RequiredFieldMissingException extends AbstractPlatformException {

    public RequiredFieldMissingException(String message) {
        super("error.msg.required.field.missing", message);
    }
}
