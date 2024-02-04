package com.example.monumentbook.utiles;

public enum UserType {
    ADMIN("ADMIN"), TEACHER("User");

    private final String type;

    UserType(String string) {
        type = string;
    }
    @Override
    public String toString() {
        return type;
    }
}
