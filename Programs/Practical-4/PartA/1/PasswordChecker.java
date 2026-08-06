public class PasswordChecker {
    public static String strength(String pw) {
        int p = 0;
        if (pw.length() >= 8) p++;
        if (pw.matches(".*[A-Z].*")) p++;
        if (pw.matches(".*\\d.*")) p++;
        if (pw.matches(".*[^a-zA-Z0-9].*")) p++;
        if (p <= 1) return "Weak";
        if (p <= 3) return "Medium";
        return "Strong";
    }
}
