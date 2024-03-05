package com.example.monumentbook.enums;

import org.springframework.http.HttpStatus;


public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND,"404 Not Found"),
    POST_SUCCESS(HttpStatus.OK, "Resource has been posted successfully"),
    DELETED_SUCCESS(HttpStatus.OK, "Resource has been deleted successfully"),
    UPDATED_SUCCESS(HttpStatus.OK, "Resource has been updated successfully"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Authentication Error. please login to get token!"),
    OK(HttpStatus.OK, "OK"),
    INCORRECT_USERNAME_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "Incorrect username or password"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden"),
    INVALID_TOKEN_SIGNATURE(HttpStatus.FORBIDDEN, "Invalid token signature"),
    INVALID_TOKEN(HttpStatus.FORBIDDEN, "Invalid token"),
    TOKEN_EXPIRED(HttpStatus.REQUEST_TIMEOUT, "Token expired"),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "Unsupported token")
    ;

    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String message) {

        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
