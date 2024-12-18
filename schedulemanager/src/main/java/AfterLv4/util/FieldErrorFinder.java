package AfterLv4.util;

import AfterLv4.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 필드 검증 확인.
 * 필드 에러 발생 시 예외 던짐
 */
public class FieldErrorFinder {
    public static void isFieldHasError(BindingResult result) {
        if (result.hasErrors()) {
            // 검증 실패 된 필드명을 예외에 포함해서 던짐
            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(FieldError::getField)
                    .toList();
            throw new ApiException("검증 오류가 발생했습니다.", HttpStatus.BAD_REQUEST, errorMessages);
        }
    }
}
