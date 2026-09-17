public class DatabaseConnection implements AutoCloseable {
    public DatabaseConnection() { System.out.println("Database connected."); }
    public void executeQuery() throws Exception {
        System.out.println("Executing query...");
        throw new Exception("Query failed!");
    }
    @Override
    public void close() {
        System.out.println("Database connection closed safely.");
    }
}
