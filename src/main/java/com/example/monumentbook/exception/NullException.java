package com.example.monumentbook.exception;

public class NullException extends RuntimeException {
    private final String title;

    public NullException(String message ,String title) {
        super(message);
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
