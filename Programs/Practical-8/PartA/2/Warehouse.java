public class Warehouse {
    private int stock = 10;
    
    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) throw new InvalidQuantityException("Quantity must be > 0");
        if (qty > stock) throw new OutOfStockException("Not enough " + item, qty - stock);
        stock -= qty;
        System.out.println("Issued " + qty + " of " + item + ". Remaining: " + stock);
    }
    
    public static void main(String[] args) {
        Warehouse w = new Warehouse();
        int[] reqs = {5, -2, 8, 2};
        for (int q : reqs) {
            try {
                System.out.println("Requesting " + q + " items...");
                w.issue("Widget", q);
            } catch (InvalidQuantityException e) {
                System.out.println("Failed: " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("Failed: " + e.getMessage() + ". Shortfall: " + e.getShortfall());
            }
        }
    }
}
