public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;
    public CinemaShow(String t, int cap) {
        this.title = t;
        this.capacity = cap;
        this.seatsAvailable = cap;
    }
    public CinemaShow(String t) { this(t, 100); }
    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }
    public void cancel(int n) {
        seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }
    public int getSeatsAvailable() { return seatsAvailable; }
    public static int getTotalBooked() { return totalBooked; }
    public static void main(String[] a) {
        CinemaShow s = new CinemaShow("Inception");
        s.book(50);
        s.book(60);
        s.cancel(10);
        s.book(20);
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());
    }
}
