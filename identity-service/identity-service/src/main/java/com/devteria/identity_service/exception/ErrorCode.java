package com.devteria.identity_service.exception;

public enum ErrorCode {
    USER_ID_NOT_EXISTED(1004,"USER_ID_NOT_EXISTED"),
    USERNAME_INVALID(1003,"username is invalid"),
    PASSWORD_INVALID(1005,"password is invalid"),
    UNAUTHORIZED(9999, "Unauthorized"),
    USER_EXISTED(1002,"user already exists"),
    ;


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
     public String getMessage() {
        return message;
   }
}
