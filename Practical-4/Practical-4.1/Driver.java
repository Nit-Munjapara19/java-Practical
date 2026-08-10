public class Driver {

    public static void main(String[] args) {

        System.out.println("Enter a Password: ");
        String password = System.console().readLine();
        System.out.println("Password entered: " + password);    


    System.out.println("length >= 8 :" + (PasswordChecker.hasMinLength(password)));
    System.out.println("has Uppercase :" + (PasswordChecker.hasUppercase(password)));
    System.out.println("has Digit :" + (PasswordChecker.hasDigit(password)));
    System.out.println("has Special Character :" + (PasswordChecker.hasSpecialChar(password)));
    System.out.println("Password Strength :" + (PasswordChecker.strength(password)));
    }
}