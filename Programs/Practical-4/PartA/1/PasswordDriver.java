public class PasswordDriver {
    public static void main(String[] a) {
        String[] pw = {"abc", "Abcd1234!"};
        for (String p : pw) System.out.println(p + " -> " + PasswordChecker.strength(p));
    }
}
