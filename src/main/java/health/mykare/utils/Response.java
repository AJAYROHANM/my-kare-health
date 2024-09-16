package health.mykare.utils;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Response {

    private String message;

    private Response() {

    }

    public Response(String message) {
        this.message = message;
    }


    public static Response of(String message) {
        return new Response(message);
    }

}
