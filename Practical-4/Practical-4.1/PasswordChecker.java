public class PasswordChecker {
    public static boolean hasMinLength(String password) {
        return password.length() >= 8;
    }

    public static boolean hasUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSpecialChar(String password) {
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";
        for (char c : password.toCharArray()) {
            if (specialChars.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    public static String strength(String password) {
        int score = 0;

        if (hasMinLength(password)) score++;
        if (hasUppercase(password)) score++;
        if (hasDigit(password)) score++;
        if (hasSpecialChar(password)) score++;

       switch (score) {
            case 4:
                return "Strong";
            case 3:
                return "Moderate";
            case 2:
                return "Weak";
            default:
                return "Very Weak";
        }
    }
}