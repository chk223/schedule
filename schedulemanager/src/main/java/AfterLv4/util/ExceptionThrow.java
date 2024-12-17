package AfterLv4.util;

import AfterLv4.exception.ApiException;
import AfterLv4.exception.ErrorMessage;

public class ExceptionThrow {
    public static void throwApiException(ErrorMessage errorMessageType) {
        throw new ApiException(errorMessageType.getMessage(), errorMessageType.getStatus());
    }
}
