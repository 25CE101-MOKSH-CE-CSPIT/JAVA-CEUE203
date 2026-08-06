import java.util.Random;
import java.util.Scanner;
enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }
public class RPSLS {
    public static int winner(Move a, Move b) {
        if (a == b) return 0;
        return switch (a.name()) {
            case "SCISSORS" -> (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;
            case "PAPER" -> (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;
            case "ROCK" -> (b == Move.LIZARD || b == Move.SCISSORS) ? 1 : -1;
            case "LIZARD" -> (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;
            case "SPOCK" -> (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
            default -> 0;
        };
    }
    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        Move[] ms = Move.values();
        int pw = 0, cw = 0;
        for (int i = 0; i < 5; i++) {
            Move c = ms[r.nextInt(ms.length)];
            System.out.print("Your move: ");
            try {
                Move p = Move.valueOf(sc.next().toUpperCase());
                System.out.println("Computer: " + c);
                int res = winner(p, c);
                if (res == 1) { System.out.println("You win round!"); pw++; }
                else if (res == -1) { System.out.println("Computer wins round!"); cw++; }
                else System.out.println("Tie!");
            } catch(Exception e) { System.out.println("Invalid. Comp wins."); cw++; }
        }
        System.out.println("You win " + pw + "-" + cw);
        sc.close();
    }
}
