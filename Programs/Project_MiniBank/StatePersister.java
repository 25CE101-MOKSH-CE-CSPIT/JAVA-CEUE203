import java.io.*;
import java.nio.file.Path;

public class StatePersister {
    public static void save(Account[] accs, Path f) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f.toFile()))) {
            oos.writeObject(accs);
        }
    }

    public static Account[] load(Path f) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f.toFile()))) {
            return (Account[]) ois.readObject();
        }
    }
}
