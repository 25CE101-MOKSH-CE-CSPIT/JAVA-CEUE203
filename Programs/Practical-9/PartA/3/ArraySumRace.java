import java.util.concurrent.atomic.AtomicLong;

class SharedSum {
    long s = 0;

    void add(long v) {
        s += v;
    }

    synchronized void syncAdd(long v) {
        s += v;
    }
}

class SumThread extends Thread {
    int[] arr;
    int lo, hi;
    SharedSum ss;
    AtomicLong al;
    int mode;

    SumThread(int[] arr, int lo, int hi, SharedSum ss, AtomicLong al, int mode) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.ss = ss;
        this.al = al;
        this.mode = mode;
    }

    public void run() {
        long p = 0;
        for (int i = lo; i < hi; i++)
            p += arr[i];
        if (mode == 0)
            ss.add(p);
        else if (mode == 1)
            ss.syncAdd(p);
        else
            al.addAndGet(p);
    }
}

public class ArraySumRace {
    public static void main(String[] a) throws Exception {
        int n = 1000000, t = 8;
        int[] arr = new int[n];
        long exp = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
            exp += arr[i];
        }
        System.out.println("Expected sum: " + exp);

        for (int r = 0; r < 5; r++) {
            SharedSum s1 = new SharedSum();
            Thread[] th = new Thread[t];
            int sz = n / t;
            for (int i = 0; i < t; i++) {
                int lo = i * sz;
                int hi = (i == t - 1) ? n : lo + sz;
                th[i] = new SumThread(arr, lo, hi, s1, null, 0);
                th[i].start();
            }
            for (int i = 0; i < t; i++)
                th[i].join();
            System.out.println("No sync (run " + (r + 1) + "): " + s1.s);
        }

        System.out.println();
        SharedSum s2 = new SharedSum();
        Thread[] th2 = new Thread[t];
        int sz = n / t;
        for (int i = 0; i < t; i++) {
            int lo = i * sz;
            int hi = (i == t - 1) ? n : lo + sz;
            th2[i] = new SumThread(arr, lo, hi, s2, null, 1);
            th2[i].start();
        }
        for (int i = 0; i < t; i++)
            th2[i].join();
        System.out.println("Fix 1 (synchronized): " + s2.s);

        AtomicLong al = new AtomicLong(0);
        Thread[] th3 = new Thread[t];
        for (int i = 0; i < t; i++) {
            int lo = i * sz;
            int hi = (i == t - 1) ? n : lo + sz;
            th3[i] = new SumThread(arr, lo, hi, null, al, 2);
            th3[i].start();
        }
        for (int i = 0; i < t; i++)
            th3[i].join();
        System.out.println("Fix 2 (AtomicLong):   " + al.get());
    }
}
