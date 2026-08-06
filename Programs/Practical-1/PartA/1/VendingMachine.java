import java.util.Scanner;
enum Coin { ONE, TWO, FIVE, TEN }
public class VendingMachine {
    public static void main(String[] a) {
        int p = 15, t = 0;
        Scanner sc = new Scanner(System.in);
        while (t < p) {
            System.out.print("Insert coin (ONE, TWO, FIVE, TEN): ");
            try {
                Coin c = Coin.valueOf(sc.next().toUpperCase());
                int v = switch (c.name()) {
                    case "ONE" -> 1;
                    case "TWO" -> 2;
                    case "FIVE" -> 5;
                    case "TEN" -> 10;
                    default -> 0;
                };
                t += v;
                System.out.println("Total so far: " + t);
            } catch (Exception e) {
                System.out.println("Invalid coin.");
            }
        }
        System.out.println("Paid. Change: " + (t - p));
        sc.close();
    }
}
