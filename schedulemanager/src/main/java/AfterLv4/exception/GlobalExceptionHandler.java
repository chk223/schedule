package AfterLv4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        List<String> errorField = e.getErrorField();
        if (errorField != null && !errorField.isEmpty()) {
            return setErrorResponseForValidation(e, errorField);
        }
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus().value(), e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    private ResponseEntity<ErrorResponse> setErrorResponseForValidation(ApiException e, List<String> errorField) {
        int errorCode = 0;
        String errorMessage = "입력 값이 잘못되었습니다.";
        if (errorField.contains("id")) errorCode = 101;
        else if (errorField.contains("password")) errorCode = 102;
        else if (errorField.contains("email")) errorCode = 103;
        else errorCode = 100;
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus().value(), errorMessage, errorCode);
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(500, "예상치 못한 오류가 발생했습니다.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
