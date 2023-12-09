package namsu.nsshop.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //Common
    ENTITY_NOT_FOUND(404, "C001", "Entity Not Found"),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
