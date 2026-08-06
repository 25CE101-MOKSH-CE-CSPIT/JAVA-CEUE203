public class Driver {
    public static void main(String[] a) {
        Point[] pts = { new Point(1,2), new Point(3,4), new Point(1,2), new Point(5,6), new Point(3,4) };
        int d = 0;
        for (int i=0; i<pts.length; i++) {
            boolean isD = true;
            for (int j=0; j<i; j++) {
                if (pts[i].equals(pts[j])) { isD = false; break; }
            }
            if (isD) d++;
        }
        System.out.println("Distinct: " + d);
    }
}
