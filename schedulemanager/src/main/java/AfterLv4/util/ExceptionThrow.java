package AfterLv4.util;

import AfterLv4.exception.ApiException;
import AfterLv4.exception.ErrorMessage;

public class ExceptionThrow {
    /**
     * 유형에 맞는 예외 던지기
     * @param errorMessageType 예외 유형
     */
    public static void throwApiException(ErrorMessage errorMessageType) {
        throw new ApiException(errorMessageType.getMessage(), errorMessageType.getStatus());
    }
}
