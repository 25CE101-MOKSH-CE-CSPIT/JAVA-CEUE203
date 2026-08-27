public class Main3 {
    public static void main(String[] args) throws Exception {
        String[] headers = {"id", "full_name", "email"};
        String[] data = {"101", "Alice Smith", "alice@example.com"};
        User u = CsvMapper.map(headers, data, User.class);
        System.out.println(u);
    }
}
