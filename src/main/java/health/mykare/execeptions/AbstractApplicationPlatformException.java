package health.mykare.execeptions;

import lombok.Getter;

@Getter
public abstract class AbstractApplicationPlatformException extends RuntimeException {

    private final String globalisationMessageCode;
    private final String defaultUserMessage;
    private final Object[] defaultUserMessageArgs;

    public AbstractApplicationPlatformException(final String globalisationMessageCode, final String defaultUserMessage,
                                                final Object... defaultUserMessageArgs) {
        this.globalisationMessageCode = globalisationMessageCode;
        this.defaultUserMessage = defaultUserMessage;
        this.defaultUserMessageArgs = defaultUserMessageArgs;
    }
}
