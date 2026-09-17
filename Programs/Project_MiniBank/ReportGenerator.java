import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    public static void generate(Path logDir, Path repFile) throws IOException {
        long td = 0;
        long tw = 0;
        List<String> rep = new ArrayList<>();
        rep.add("END-OF-DAY TRANSACTION REPORT");
        rep.add("=============================");

        if (Files.exists(logDir)) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(logDir, "*.log")) {
                for (Path p : ds) {
                    BasicFileAttributes at = Files.readAttributes(p, BasicFileAttributes.class);
                    rep.add("Log: " + p.getFileName() + " | Size: " + at.size() + "B | Mod: " + at.lastModifiedTime());
                    try (BufferedReader br = Files.newBufferedReader(p)) {
                        String ln;
                        while ((ln = br.readLine()) != null) {
                            String[] sp = ln.split(" ");
                            if (sp.length >= 3) {
                                String op = sp[0].toUpperCase();
                                long amt = Long.parseLong(sp[2]);
                                if (op.equals("DEPOSIT")) td += amt;
                                else if (op.equals("WITHDRAW")) tw += amt;
                            }
                        }
                    }
                }
            }
        }

        rep.add("Total Deposits: " + td);
        rep.add("Total Withdrawals: " + tw);
        rep.add("Net Change: " + (td - tw));

        if (repFile.getParent() != null && !Files.exists(repFile.getParent())) {
            Files.createDirectories(repFile.getParent());
        }
        Files.write(repFile, rep);
    }
}
