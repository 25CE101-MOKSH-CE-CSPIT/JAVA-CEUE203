import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TreeReport {
    public static void main(String[] a) throws IOException {
        Path root = Paths.get(".");
        Path out = Paths.get("tree_report.txt");
        List<String> lines = new ArrayList<>();
        lines.add("FILE TREE REPORT");
        lines.add("----------------");

        try (Stream<Path> st = Files.walk(root)) {
            st.filter(Files::isRegularFile).forEach(p -> {
                try {
                    BasicFileAttributes at = Files.readAttributes(p, BasicFileAttributes.class);
                    lines.add(p.getFileName() + " | " + at.size() + " bytes | " + at.lastModifiedTime());
                } catch (IOException e) {
                }
            });
        }

        Files.write(out, lines);
        System.out.println("Report written to " + out.toAbsolutePath());
    }
}
