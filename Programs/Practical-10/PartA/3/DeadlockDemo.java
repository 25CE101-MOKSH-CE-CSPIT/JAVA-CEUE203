public class DeadlockDemo {

    static Object l1 = new Object();
    static Object l2 = new Object();

    static void withDeadlock() {
        Thread t1 = new Thread(() -> {
            synchronized (l1) {
                System.out.println("Thread-1: locked l1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Thread-1: waiting for l2...");
                synchronized (l2) {
                    System.out.println("Thread-1: locked l2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (l2) {
                System.out.println("Thread-2: locked l2");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Thread-2: waiting for l1...");
                synchronized (l1) {
                    System.out.println("Thread-2: locked l1");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join(3000);
            t2.join(3000);
        } catch (InterruptedException e) {}

        if (t1.isAlive() || t2.isAlive()) {
            System.out.println("\n*** DEADLOCK DETECTED! Threads are stuck. ***\n");
            t1.interrupt();
            t2.interrupt();
        }
    }

    static void withoutDeadlock() {
        Thread t1 = new Thread(() -> {
            synchronized (l1) {
                System.out.println("Thread-1: locked l1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (l2) {
                    System.out.println("Thread-1: locked l2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (l1) {
                System.out.println("Thread-2: locked l1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (l2) {
                    System.out.println("Thread-2: locked l2");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}

        System.out.println("Both threads completed. No deadlock!\n");
    }

    public static void main(String[] a) {
        System.out.println("=== Demonstrating DEADLOCK (opposite lock order) ===");
        withDeadlock();

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        System.out.println("=== Fixing with CONSISTENT lock order ===");
        withoutDeadlock();
    }
}
