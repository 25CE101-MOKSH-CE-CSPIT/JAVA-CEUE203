import java.util.regex.*;
public class Validator {
    public static boolean isValidMobile(String m) { return Pattern.matches("[6-9]\\d{9}", m); }
    public static boolean isValidEmail(String e) { return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", e); }
    public static boolean isValidPan(String p) { return Pattern.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}", p); }
    public static boolean isValidIfsc(String i) { return Pattern.matches("^[A-Z]{4}0[A-Z0-9]{6}$", i); }
}
