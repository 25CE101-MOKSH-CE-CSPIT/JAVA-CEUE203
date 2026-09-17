import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;

public class TransactionLog {
    public static void append(Path f, String ln) throws IOException {
        if (f.getParent() != null && !Files.exists(f.getParent())) {
            Files.createDirectories(f.getParent());
        }
        Files.write(f, Collections.singletonList(ln), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
