package com.example.monumentbook.utilities.methods;

public class ValidateMethods {

    public static final int MAX_CAUSE_LENGTH = 120;
    public static final int MAX_LOGIN_LENGTH = 40;
    public static final int MAX_STRING_LENGTH = 40;

    public static String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

//    public static boolean isValidDateType(String dateType) {
//        return !dateType.equals(DateType.MORNING.getValue()) && !dateType.equals(DateType.AFTERNOON.getValue());
//    }

    public static boolean isValidCause(String cause) {
        return cause != null && cause.length() > MAX_CAUSE_LENGTH;
    }

    public static boolean isValidStringL(String cause) {
        return cause != null && cause.length() > MAX_STRING_LENGTH;
    }

    public static boolean isValidLogin(String login) {
        return login != null && login.length() > MAX_LOGIN_LENGTH;
    }

    public static boolean isSymbolInput(String input) {
        return input != null && input.matches("^[^a-zA-Z0-9]+$");
    }


//    public static boolean isValidLanguageCode(String langCode) {
//        for (LangCode code : LangCode.values()) {
//            if (code.getCode().equalsIgnoreCase(langCode)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
