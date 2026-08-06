public class Thermostat {
    private String location;
    private int temperature;
    private static final int MIN = 16, MAX = 30;
    private static int activeCount = 0;
    public Thermostat(String loc, int st) {
        this.location = loc;
        this.temperature = (st >= MIN && st <= MAX) ? st : 22;
        activeCount++;
    }
    public Thermostat(String loc) { this(loc, 22); }
    public void raise() {
        if (temperature < MAX) temperature++;
        else System.out.println("Already at max");
    }
    public void lower() {
        if (temperature > MIN) temperature--;
        else System.out.println("Already at min");
    }
    public int getTemperature() { return temperature; }
    public static int getActiveCount() { return activeCount; }
    public static void main(String[] a) {
        Thermostat t1 = new Thermostat("Living Room", 25);
        Thermostat t2 = new Thermostat("Bedroom");
        for (int i=0; i<10; i++) { t1.raise(); System.out.println(t1.getTemperature()); }
        for (int i=0; i<20; i++) { t1.lower(); System.out.println(t1.getTemperature()); }
        System.out.println("Active: " + Thermostat.getActiveCount());
    }
}
