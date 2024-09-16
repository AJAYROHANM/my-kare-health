package health.mykare.data;

import lombok.Data;

@Data
public final class ApiErrorMessageArg {

    /**
     * The actual value of the parameter (if any) as passed to API.
     */
    private Object value;

    public static ApiErrorMessageArg from(final Object object) {
        return new ApiErrorMessageArg(object);
    }

    public ApiErrorMessageArg(final Object object) {
        this.value = object;
    }
}
