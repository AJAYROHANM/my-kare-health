package health.mykare.data;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ApiParameterError {

    /**
     * A developer friendly plain English description of why the HTTP error response was returned from the API.
     */
    private final String developerMessage;

    /**
     * A user friendly plain English description of why the HTTP error response was returned from the API that can be
     * presented to end users.
     */
    private final String defaultUserMessage;

    /**
     * A code that can be used for globalisation support by client applications of the API.
     */
    private final String userMessageGlobalisationCode;

    /**
     * The name of the field or parameter passed to the API that this error relates to.
     */
    private String parameterName;

    /**
     * The actual value of the parameter (if any) as passed to API.
     */
    private final Object value;

    /**
     * Arguments related to the user error message.
     */
    private final List<ApiErrorMessageArg> args;

    private ApiParameterError(final String globalisationMessageCode, final String defaultUserMessage, final Object[] defaultUserMessageArgs,
                              String parameterName, String value) {
        this.userMessageGlobalisationCode = globalisationMessageCode;
        this.developerMessage = defaultUserMessage;
        this.defaultUserMessage = defaultUserMessage;
        this.parameterName = parameterName;
        this.value = value;

        final List<ApiErrorMessageArg> messageArgs = new ArrayList<>();
        if (defaultUserMessageArgs != null) {
            for (final Object object : defaultUserMessageArgs) {
                if (object instanceof Date) {
                    final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    final String formattedDate = dateFormatter.format(object);
                    messageArgs.add(ApiErrorMessageArg.from(formattedDate));
                } else {
                    messageArgs.add(ApiErrorMessageArg.from(object));
                }
            }
        }
        this.args = messageArgs;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public String getDefaultUserMessage() {
        return this.defaultUserMessage;
    }

    public String getUserMessageGlobalisationCode() {
        return this.userMessageGlobalisationCode;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public void setParameterName(final String parameterName) {
        this.parameterName = parameterName;
    }

    public Object getValue() {
        return this.value;
    }

    public static ApiParameterError parameterError(final String globalisationMessageCode, final String defaultUserMessage,
                                                   final String parameterName, final Object... defaultUserMessageArgs) {
        final ApiParameterError error = new ApiParameterError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs,
                parameterName, null);
        return error;
    }

}
