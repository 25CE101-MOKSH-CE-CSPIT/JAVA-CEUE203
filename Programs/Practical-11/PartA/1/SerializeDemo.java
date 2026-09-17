import java.io.*;
import java.util.*;

class Item implements Serializable {
    String n;
    double p;
    transient String tmp;

    Item(String n, double p, String tmp) {
        this.n = n;
        this.p = p;
        this.tmp = tmp;
    }

    public String toString() {
        return "Item[n=" + n + ", p=" + p + ", tmp=" + tmp + "]";
    }
}

public class SerializeDemo {
    public static void main(String[] a) {
        String f = "items.ser";
        Item[] arr = {
            new Item("Pen", 10.5, "secret1"),
            new Item("Book", 250.0, "secret2"),
            new Item("Bag", 999.9, "secret3")
        };

        System.out.println("=== Before Saving ===");
        for (Item it : arr)
            System.out.println(it);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(arr);
            System.out.println("\nObjects saved to " + f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Item[] res = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            res = (Item[]) ois.readObject();
            System.out.println("\nObjects loaded from " + f);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== After Loading ===");
        for (Item it : res)
            System.out.println(it);

        System.out.println("\nNote: 'tmp' field is transient, so it becomes null after deserialization.");
    }
}
