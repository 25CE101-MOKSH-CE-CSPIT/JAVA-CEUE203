abstract class Media {
    String title;
    Media(String title) { this.title = title; }
    abstract double computeLateFee(int daysLate);
}

class Book extends Media {
    Book(String title) { super(title); }
    double computeLateFee(int daysLate) { return daysLate * 0.50; }
}

class DVD extends Media {
    DVD(String title) { super(title); }
    double computeLateFee(int daysLate) { return daysLate * 2.00; }
}

class Magazine extends Media {
    Magazine(String title) { super(title); }
    double computeLateFee(int daysLate) { return daysLate * 0.25; }
}

public class MediaLateFee {
    public static void main(String[] args) {
        Media[] returnedBatch = {
            new Book("Java Programming"),
            new DVD("Inception"),
            new Magazine("Tech Today")
        };
        int[] daysLate = { 5, 2, 10 }; // corresponding days late
        double totalFees = 0;
        for (int i = 0; i < returnedBatch.length; i++) {
            double fee = returnedBatch[i].computeLateFee(daysLate[i]);
            System.out.println(returnedBatch[i].title + " late fee: $" + fee);
            totalFees += fee;
        }
        System.out.println("Total late fees: $" + totalFees);
    }
}
