import java.util.InputMismatchException;
import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter two numbers: ");
                int a = sc.nextInt();
                int b = sc.nextInt();
                System.out.print("Enter operator (+, -, *, /): ");
                String op = sc.next();
                
                int res = 0;
                if (op.equals("+")) res = a + b;
                else if (op.equals("-")) res = a - b;
                else if (op.equals("*")) res = a * b;
                else if (op.equals("/")) {
                    if (b == 0) throw new DivideByZeroException("Cannot divide by zero!");
                    res = a / b;
                } else {
                    System.out.println("Invalid operator");
                    continue;
                }
                System.out.println("Result: " + res);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter valid numbers.");
                sc.next();
            } catch (DivideByZeroException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Attempt logged.");
            }
        }
        sc.close();
    }
}
