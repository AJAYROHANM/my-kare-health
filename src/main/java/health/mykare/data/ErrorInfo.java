package health.mykare.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorInfo {

    private int statusCode;
    private String message;
    private Object[] fields;
    private String errorCode;
    private List<ApiParameterError> fieldErrors;
}
