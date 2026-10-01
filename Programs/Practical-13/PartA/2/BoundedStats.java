public class BoundedStats {
    static <N extends Number> double average(N[] v) {
        double s = 0;
        for (N n : v)
            s += n.doubleValue();
        return s / v.length;
    }

    public static void main(String[] args) {
        Integer[] ia = {10, 20, 30, 40, 50};
        System.out.println("Integer avg: " + average(ia));

        Double[] da = {1.5, 2.5, 3.5, 4.5};
        System.out.println("Double avg: " + average(da));

        Long[] la = {100L, 200L, 300L};
        System.out.println("Long avg: " + average(la));

        System.out.println("\nNote: average(new String[]{...}) won't compile");
        System.out.println("because String does not extend Number.");
        System.out.println("The bound <N extends Number> ensures only");
        System.out.println("numeric types are accepted.");
    }
}
