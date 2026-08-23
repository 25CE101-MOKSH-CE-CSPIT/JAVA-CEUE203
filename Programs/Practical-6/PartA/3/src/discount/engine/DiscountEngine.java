package discount.engine;

import discount.rules.DiscountRule;
import java.util.Scanner;

public class DiscountEngine {
    public static void main(String[] a) {
        double[] pr = {100, 200, 350, 500, 750};

        DiscountRule r1 = p -> p * 0.90;
        DiscountRule r2 = p -> p * 0.80;
        DiscountRule r3 = p -> p > 300 ? p * 0.75 : p * 0.95;

        Scanner sc = new Scanner(System.in);
        System.out.println("Select discount rule:");
        System.out.println("1. 10% off");
        System.out.println("2. 20% off");
        System.out.println("3. 25% off (>300) else 5% off");
        int c = sc.nextInt();

        DiscountRule r;
        switch (c) {
            case 1: r = r1; break;
            case 2: r = r2; break;
            case 3: r = r3; break;
            default: r = p -> p; break;
        }

        System.out.println("Original -> Discounted:");
        for (double p : pr)
            System.out.printf("%.2f -> %.2f%n", p, r.apply(p));

        sc.close();
    }
}
