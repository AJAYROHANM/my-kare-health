package health.mykare.user.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorInfo {

    private int statusCode;
    private String message;
    private Object fields;
    private String errorCode;
}
