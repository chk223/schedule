package AfterLv4.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final List<String> errorField;
    public ApiException(String message, HttpStatus statusCode) {
        super(message);
        this.status = statusCode;
        this.errorField = null;
    }

    public ApiException(String message, HttpStatus statusCode, List<String> errorField) {
        super(message);
        this.status = statusCode;
        this.errorField = errorField;
    }
}
