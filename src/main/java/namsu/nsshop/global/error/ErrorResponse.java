package namsu.nsshop.global.error;

import lombok.Getter;
import namsu.nsshop.global.error.exception.ErrorCode;

@Getter
public record ErrorResponse(String message, String code) {

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getCode());
    }
}
