package health.mykare.execeptions;

public class InvalidDataFormatException extends AbstractPlatformException{

    public InvalidDataFormatException(String message) {
        super("error.msg.duplicate", message);
    }
}