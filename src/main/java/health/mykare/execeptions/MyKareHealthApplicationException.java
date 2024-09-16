package health.mykare.execeptions;



public class MyKareHealthApplicationException extends AbstractApplicationPlatformException {

    public MyKareHealthApplicationException(String message) {
        super("error.msg.not.found", message);
    }
}
