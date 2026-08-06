import java.util.Scanner;
record Vehicle(String number, String type) {}
public class TollBooth {
    public static void main(String[] a) {
        int tot = 0, b = 0, c = 0, t = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter vehicle number (or 'done'): ");
            String n = sc.next();
            if (n.equalsIgnoreCase("done")) break;
            System.out.print("Enter vehicle type (bike, car, truck): ");
            String ty = sc.next().toLowerCase();
            Vehicle v = new Vehicle(n, ty);
            int toll = switch (v.type()) {
                case "bike" -> { b++; yield 20; }
                case "car" -> { c++; yield 50; }
                case "truck" -> { t++; yield 150; }
                default -> 0;
            };
            if (toll > 0) tot += toll;
            else System.out.println("Invalid type.");
        }
        System.out.println("Total toll: " + tot);
        String f = "bike";
        int m = b;
        if (c > m) { m = c; f = "car"; }
        if (t > m) { m = t; f = "truck"; }
        System.out.println("Most frequent: " + f);
        sc.close();
    }
}
