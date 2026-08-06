public class ParkingLot {
    private int twoWheelers, fourWheelers;
    private final int twoCap = 10, fourCap = 10;
    private static long revenue = 0;
    public void park(String type) {
        if (type.equals("two")) {
            if (twoWheelers < twoCap) { twoWheelers++; revenue += 20; }
            else System.out.println("Full");
        } else if (type.equals("four")) {
            if (fourWheelers < fourCap) { fourWheelers++; revenue += 40; }
            else System.out.println("Full");
        } else System.out.println("Invalid");
    }
    public void leave(String type) {
        if (type.equals("two") && twoWheelers > 0) twoWheelers--;
        else if (type.equals("four") && fourWheelers > 0) fourWheelers--;
    }
    public static void main(String[] a) {
        ParkingLot p = new ParkingLot();
        p.park("two"); p.park("four"); p.park("two");
        p.leave("two");
        System.out.println("Revenue: " + revenue);
    }
}
