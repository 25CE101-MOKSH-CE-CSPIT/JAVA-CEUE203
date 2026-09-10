class Booking {
    int s;
    int b = 0;

    Booking(int s) {
        this.s = s;
    }

    boolean book(String nm) {
        if (s > 0) {
            try { Thread.sleep(1); } catch (Exception e) {}
            s--;
            b++;
            System.out.println(nm + " booked. Seats left: " + s);
            return true;
        }
        System.out.println(nm + " failed. No seats.");
        return false;
    }

    synchronized boolean syncBook(String nm) {
        if (s > 0) {
            try { Thread.sleep(1); } catch (Exception e) {}
            s--;
            b++;
            System.out.println(nm + " booked. Seats left: " + s);
            return true;
        }
        System.out.println(nm + " failed. No seats.");
        return false;
    }
}

class BookThread extends Thread {
    Booking bk;
    String nm;
    boolean sync;

    BookThread(Booking bk, String nm, boolean sync) {
        this.bk = bk;
        this.nm = nm;
        this.sync = sync;
    }

    public void run() {
        if (sync)
            bk.syncBook(nm);
        else
            bk.book(nm);
    }
}

public class SeatBooking {
    public static void main(String[] a) throws Exception {
        System.out.println("=== Without Synchronization ===");
        Booking b1 = new Booking(5);
        Thread[] th = new Thread[10];
        for (int i = 0; i < 10; i++) {
            th[i] = new BookThread(b1, "User" + (i + 1), false);
            th[i].start();
        }
        for (int i = 0; i < 10; i++)
            th[i].join();
        System.out.println("Total booked: " + b1.b + " (should be 5)\n");

        System.out.println("=== With Synchronization ===");
        Booking b2 = new Booking(5);
        Thread[] th2 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            th2[i] = new BookThread(b2, "User" + (i + 1), true);
            th2[i].start();
        }
        for (int i = 0; i < 10; i++)
            th2[i].join();
        System.out.println("Total booked: " + b2.b + " (should be 5)");
    }
}
