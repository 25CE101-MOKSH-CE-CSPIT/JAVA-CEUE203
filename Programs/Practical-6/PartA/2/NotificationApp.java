@FunctionalInterface
interface Notifier {
    void send(String m);
}

interface Urgent {}

class UrgentEmail implements Notifier, Urgent {
    public void send(String m) {
        System.out.println("Email [URGENT]: " + m);
    }
}

public class NotificationApp {
    public static void main(String[] a) {
        Notifier e = m -> System.out.println("Email: " + m);
        Notifier s = m -> System.out.println("SMS: " + m);
        Notifier u = new UrgentEmail();

        Notifier[] n = { e, s, u };
        String msg = "Server is down!";

        System.out.println("=== Broadcasting ===");
        for (Notifier x : n) {
            x.send(msg);
            if (x instanceof Urgent)
                x.send(msg);
        }
    }
}
