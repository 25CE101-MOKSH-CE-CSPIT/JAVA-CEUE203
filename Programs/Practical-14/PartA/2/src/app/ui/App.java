package app.ui;

import app.model.Student;
import app.service.StudentService;
import java.util.Scanner;

public class App {
    public static void runTests() {
        System.out.println("--- Running Persistence Tests ---");
        StudentService sv1 = new StudentService();
        sv1.add(new Student(1, "Alice"));
        sv1.add(new Student(2, "Bob"));

        String f = "students.dat";
        try {
            sv1.save(f);
            System.out.println("PASS: Data saved");
        } catch (Exception e) {
            System.out.println("FAIL: Save error: " + e.getMessage());
        }

        StudentService sv2 = new StudentService();
        try {
            sv2.load(f);
            boolean eq = sv2.count() == 2 && sv2.get(0).equals(new Student(1, "Alice")) && sv2.get(1).equals(new Student(2, "Bob"));
            if (eq) {
                System.out.println("PASS: Reloaded data unchanged");
            } else {
                System.out.println("FAIL: Data mismatch");
            }
        } catch (Exception e) {
            System.out.println("FAIL: Load error: " + e.getMessage());
        }
    }

    public static void main(String[] a) {
        if (a.length > 0 && a[0].equals("test")) {
            runTests();
            return;
        }

        Scanner sc = new Scanner(System.in);
        StudentService sv = new StudentService();
        int ch;
        do {
            System.out.println("\n1.Add 2.Display 3.Count 4.Save 5.Load 6.Test 7.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    sv.add(new Student(id, n));
                    System.out.println("Added.");
                    break;
                case 2:
                    sv.display();
                    break;
                case 3:
                    System.out.println("Count: " + sv.count());
                    break;
                case 4:
                    try {
                        sv.save("students.dat");
                        System.out.println("Saved.");
                    } catch (Exception e) {
                        System.out.println("Error saving.");
                    }
                    break;
                case 5:
                    try {
                        sv.load("students.dat");
                        System.out.println("Loaded.");
                    } catch (Exception e) {
                        System.out.println("Error loading.");
                    }
                    break;
                case 6:
                    runTests();
                    break;
                case 7:
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("Invalid.");
            }
        } while (ch != 7);
        sc.close();
    }
}
