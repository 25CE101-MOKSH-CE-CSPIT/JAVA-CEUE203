import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class LogAnalyzer {
    public static void main(String[] a) throws IOException {
        Path d = Paths.get("logs");
        Files.createDirectories(d);

        String[][] data = {
            {"log1.txt", "INFO: started\nERROR: disk full\nINFO: retrying\nERROR: timeout\n"},
            {"log2.txt", "INFO: connected\nINFO: processing\nWARN: slow query\n"},
            {"log3.txt", "ERROR: null pointer\nINFO: recovered\nERROR: out of memory\nINFO: shutdown\n"}
        };

        for (String[] e : data)
            Files.writeString(d.resolve(e[0]), e[1]);

        System.out.println("Created log files in 'logs' folder.\n");

        String kw = "ERROR";
        int tl = 0, kc = 0;

        for (String[] e : data) {
            Path p = d.resolve(e[0]);
            var lines = Files.readAllLines(p);
            int lc = lines.size();
            int mc = 0;
            for (String ln : lines) {
                if (ln.contains(kw))
                    mc++;
            }
            tl += lc;
            kc += mc;

            BasicFileAttributes at = Files.readAttributes(p, BasicFileAttributes.class);
            System.out.println("File: " + p);
            System.out.println("  Size: " + at.size() + " bytes");
            System.out.println("  Lines: " + lc);
            System.out.println("  Lines with '" + kw + "': " + mc);
            System.out.println();
        }

        System.out.println("=== Summary ===");
        System.out.println("Total lines across all files: " + tl);
        System.out.println("Total lines containing '" + kw + "': " + kc);
    }
}
