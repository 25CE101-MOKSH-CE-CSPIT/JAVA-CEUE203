class GStack<T> {
    private Object[] a;
    private int t;

    GStack(int c) {
        a = new Object[c];
        t = -1;
    }

    void push(T v) {
        if (t == a.length - 1)
            throw new RuntimeException("Stack full");
        a[++t] = v;
    }

    @SuppressWarnings("unchecked")
    T pop() {
        if (t == -1)
            throw new RuntimeException("Stack empty");
        return (T) a[t--];
    }

    @SuppressWarnings("unchecked")
    T peek() {
        if (t == -1)
            throw new RuntimeException("Stack empty");
        return (T) a[t];
    }

    boolean isEmpty() { return t == -1; }
    int size() { return t + 1; }
}

public class GenericStack {
    public static void main(String[] args) {
        GStack<Integer> s1 = new GStack<>(5);
        System.out.println("=== Integer Stack ===");
        s1.push(10);
        s1.push(20);
        s1.push(30);
        System.out.println("Peek: " + s1.peek());
        System.out.println("Size: " + s1.size());
        while (!s1.isEmpty())
            System.out.println("Pop: " + s1.pop());

        System.out.println("\nHandling empty stack:");
        try {
            s1.pop();
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        GStack<String> s2 = new GStack<>(3);
        System.out.println("\n=== String Stack ===");
        s2.push("Java");
        s2.push("Generics");
        s2.push("Stack");
        System.out.println("Peek: " + s2.peek());
        while (!s2.isEmpty())
            System.out.println("Pop: " + s2.pop());
    }
}
