import java.util.*;

class Student {
    int id;
    String n;

    Student(int id, String n) {
        this.id = id;
        this.n = n;
    }

    public String toString() {
        return id + " " + n;
    }
}

class StudentService {
    ArrayList<Student> ls = new ArrayList<>();

    void add(Student s) {
        ls.add(s);
    }

    int count() {
        return ls.size();
    }

    Student get(int i) {
        if (i < 0 || i >= ls.size())
            throw new IndexOutOfBoundsException("Invalid index");
        return ls.get(i);
    }

    void remove(int i) {
        if (i < 0 || i >= ls.size())
            throw new IndexOutOfBoundsException("Invalid index");
        ls.remove(i);
    }

    void display() {
        for (Student s : ls)
            System.out.println(s);
    }
}

class TestRunner {
    static int p = 0, f = 0;

    static void test(String nm, boolean c) {
        if (c) {
            System.out.println("PASS: " + nm);
            p++;
        } else {
            System.out.println("FAIL: " + nm);
            f++;
        }
    }

    static void run() {
        System.out.println("--- Running Tests ---");

        StudentService sv = new StudentService();
        sv.add(new Student(1, "Alice"));
        test("Add increases count to 1", sv.count() == 1);

        sv.add(new Student(2, "Bob"));
        test("Add increases count to 2", sv.count() == 2);

        boolean ex = false;
        try {
            sv.get(10);
        } catch (IndexOutOfBoundsException e) {
            ex = true;
        }
        test("Invalid index throws exception", ex);

        System.out.println("\nResults: " + p + " passed, " + f + " failed");
    }
}

public class App {
    public static void main(String[] a) {
        if (a.length > 0 && a[0].equals("test")) {
            TestRunner.run();
            return;
        }
        Scanner sc = new Scanner(System.in);
        StudentService sv = new StudentService();
        int ch;
        do {
            System.out.println("\n1.Add 2.Display 3.Remove 4.Count 5.Exit");
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
                    System.out.print("Index: ");
                    int i = sc.nextInt();
                    try {
                        sv.remove(i);
                        System.out.println("Removed.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Count: " + sv.count());
                    break;
                case 5:
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (ch != 5);
        sc.close();
    }
}
