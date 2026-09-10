class Counter {
    int c = 0;

    void inc() {
        c++;
    }

    synchronized void syncInc() {
        c++;
    }
}

class IncThread extends Thread {
    Counter ct;
    int n;
    boolean sync;

    IncThread(Counter ct, int n, boolean sync) {
        this.ct = ct;
        this.n = n;
        this.sync = sync;
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            if (sync)
                ct.syncInc();
            else
                ct.inc();
        }
    }
}

public class CounterRace {
    public static void main(String[] a) throws Exception {
        int t = 5, n = 100000;

        Counter c1 = new Counter();
        Thread[] th = new Thread[t];
        for (int i = 0; i < t; i++) {
            th[i] = new IncThread(c1, n, false);
            th[i].start();
        }
        for (int i = 0; i < t; i++)
            th[i].join();
        System.out.println("Without sync: " + c1.c + " (expected " + (t * n) + ")");

        Counter c2 = new Counter();
        Thread[] th2 = new Thread[t];
        for (int i = 0; i < t; i++) {
            th2[i] = new IncThread(c2, n, true);
            th2[i].start();
        }
        for (int i = 0; i < t; i++)
            th2[i].join();
        System.out.println("With sync:    " + c2.c + " (expected " + (t * n) + ")");
    }
}
