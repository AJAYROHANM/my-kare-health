package health.mykare.execeptions;

public class DuplicateRecordException extends AbstractPlatformException{

    public DuplicateRecordException(String message) {
        super("error.msg.duplicate", message);
    }
}