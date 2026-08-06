public class CommandParser {
    public static Command parse(String line) {
        String[] p = line.split(" ");
        if (p.length != 3) throw new IllegalArgumentException("Invalid");
        return new Command(TransactionType.valueOf(p[0].toUpperCase()), p[1], Long.parseLong(p[2]));
    }
}
