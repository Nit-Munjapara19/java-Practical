package util;

public final class Validator {
    private Validator() {}
    public static void requireText(String value, String field) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(field + " cannot be empty.");
    }
}