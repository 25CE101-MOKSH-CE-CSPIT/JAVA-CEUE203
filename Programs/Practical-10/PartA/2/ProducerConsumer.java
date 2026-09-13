import java.util.LinkedList;

class Buffer {
    LinkedList<Integer> buf = new LinkedList<>();
    int cap;

    Buffer(int cap) {
        this.cap = cap;
    }

    synchronized void produce(int v) throws InterruptedException {
        while (buf.size() == cap) {
            wait();
        }
        buf.add(v);
        System.out.println("Produced: " + v);
        notify();
    }

    synchronized int consume() throws InterruptedException {
        while (buf.isEmpty()) {
            wait();
        }
        int v = buf.removeFirst();
        System.out.println("Consumed: " + v);
        notify();
        return v;
    }
}

class Producer implements Runnable {
    Buffer b;
    int n;

    Producer(Buffer b, int n) {
        this.b = b;
        this.n = n;
    }

    public void run() {
        try {
            for (int i = 1; i <= n; i++) {
                b.produce(i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    Buffer b;
    int n;

    Consumer(Buffer b, int n) {
        this.b = b;
        this.n = n;
    }

    public void run() {
        try {
            for (int i = 1; i <= n; i++) {
                b.consume();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] a) throws InterruptedException {
        int n = 10;
        Buffer b = new Buffer(3);
        Thread t1 = new Thread(new Producer(b, n));
        Thread t2 = new Thread(new Consumer(b, n));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("All " + n + " items produced and consumed. None lost.");
    }
}
