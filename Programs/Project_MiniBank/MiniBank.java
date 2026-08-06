public class MiniBank {
    public static void main(String[] args) {
        System.out.println(Validator.isValidMobile("9876543210"));
        System.out.println(Validator.isValidMobile("12345"));
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(cmd.type() + " " + cmd.accountNumber() + " " + cmd.amount());
    }
}
