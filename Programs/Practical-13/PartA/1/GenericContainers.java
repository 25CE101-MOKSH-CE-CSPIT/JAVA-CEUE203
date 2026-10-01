class Box<T> {
    private T v;

    T get() { return v; }
    void set(T v) { this.v = v; }
}

class Pair<A, B> {
    private A a;
    private B b;

    Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    A getFirst() { return a; }
    B getSecond() { return b; }
}

public class GenericContainers {
    static <T> T first(T[] arr) {
        return arr[0];
    }

    public static void main(String[] args) {
        Box<Integer> b1 = new Box<>();
        b1.set(42);
        int n = b1.get();
        System.out.println("Integer Box: " + n);

        Box<String> b2 = new Box<>();
        b2.set("Hello");
        String s = b2.get();
        System.out.println("String Box: " + s);

        Pair<String, Integer> p = new Pair<>("Java", 17);
        System.out.println("Pair: (" + p.getFirst() + ", " + p.getSecond() + ")");

        Pair<Double, Boolean> p2 = new Pair<>(3.14, true);
        System.out.println("Pair: (" + p2.getFirst() + ", " + p2.getSecond() + ")");

        String[] sa = {"Apple", "Banana", "Cherry"};
        System.out.println("First element (String[]): " + first(sa));

        Integer[] ia = {10, 20, 30};
        System.out.println("First element (Integer[]): " + first(ia));
    }
}
