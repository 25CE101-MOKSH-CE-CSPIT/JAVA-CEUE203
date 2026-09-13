import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    int id;

    Task(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Task " + id + " running on " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + id + " completed on " + Thread.currentThread().getName());
    }
}

public class ThreadPoolDemo {
    public static void main(String[] a) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            es.submit(new Task(i));
        }
        es.shutdown();
        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed. Threads were reused.");
    }
}
