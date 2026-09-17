abstract class Employee {
    String name;
    String id;
    Employee(String name, String id) { this.name = name; this.id = id; }
    abstract double monthlySalary();
}

class FullTime extends Employee {
    double fixedSalary;
    FullTime(String name, String id, double fixedSalary) { 
        super(name, id); 
        this.fixedSalary = fixedSalary; 
    }
    double monthlySalary() { return fixedSalary; }
}

class PartTime extends Employee {
    double hours, rate;
    PartTime(String name, String id, double hours, double rate) { 
        super(name, id); 
        this.hours = hours; 
        this.rate = rate; 
    }
    double monthlySalary() { return hours * rate; }
}

class Intern extends Employee {
    double stipend;
    Intern(String name, String id, double stipend) { 
        super(name, id); 
        this.stipend = stipend; 
    }
    double monthlySalary() { return stipend; }
}

public class Payroll {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Alice", "F01", 5000),
            new PartTime("Bob", "P01", 80, 20),
            new Intern("Charlie", "I01", 1000)
        };
        double totalSalary = 0;
        for (Employee e : employees) {
            double salary = e.monthlySalary();
            System.out.print(e.name + " (" + e.id + "): " + salary);
            if (e instanceof Intern) {
                System.out.print(" (Intern stipend)");
            }
            System.out.println();
            totalSalary += salary;
        }
        System.out.println("Total Salary: " + totalSalary);
    }
}
