interface Switchable {
    void on();
    void off();
    default void toggle() {
        on();
        off();
    }
}

@FunctionalInterface
interface SwitchPolicy {
    boolean canSwitchOn(Switchable s, int h);
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }
    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }
    public void off() {
        System.out.println("Light is OFF");
    }
}

public class RemoteControl {
    public static void main(String[] a) {
        Switchable[] d = { new Fan(), new Light() };
        System.out.println("=== Toggling all devices ===");
        for (Switchable s : d)
            s.toggle();

        System.out.println("\n=== SwitchPolicy via Anonymous Class ===");
        SwitchPolicy p1 = new SwitchPolicy() {
            public boolean canSwitchOn(Switchable s, int h) {
                return h >= 6 && h <= 22;
            }
        };

        System.out.println("\n=== SwitchPolicy via Lambda ===");
        SwitchPolicy p2 = (s, h) -> h >= 18;

        Fan f = new Fan();
        Light l = new Light();
        int hr = 20;

        System.out.println("AnonClass: Fan@" + hr + "h allowed=" + p1.canSwitchOn(f, hr));
        if (p1.canSwitchOn(f, hr)) f.on();

        System.out.println("Lambda: Light@" + hr + "h allowed=" + p2.canSwitchOn(l, hr));
        if (p2.canSwitchOn(l, hr)) l.on();

        hr = 3;
        System.out.println("\nAnonClass: Fan@" + hr + "h allowed=" + p1.canSwitchOn(f, hr));
        System.out.println("Lambda: Light@" + hr + "h allowed=" + p2.canSwitchOn(l, hr));
    }
}
