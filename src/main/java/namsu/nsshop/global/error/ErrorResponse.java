package namsu.nsshop.global.error;

import namsu.nsshop.global.error.exception.ErrorCode;

public record ErrorResponse(String message, String code) {

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getCode());
    }
}
