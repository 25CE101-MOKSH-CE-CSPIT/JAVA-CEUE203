public class Main3 {
    public static void main(String[] args) {
        try (DatabaseConnection db = new DatabaseConnection()) {
            db.executeQuery();
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
