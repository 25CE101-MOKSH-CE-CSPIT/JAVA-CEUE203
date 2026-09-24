import java.util.*;

public class UniqueVisitors {
    public static void main(String[] a) {
        String[] lg = {"alice", "bob", "charlie", "alice", "dave", "bob",
                        "eve", "charlie", "alice", "frank", "dave", "eve"};
        HashSet<String> hs = new HashSet<>(Arrays.asList(lg));
        LinkedHashSet<String> ls = new LinkedHashSet<>(Arrays.asList(lg));
        System.out.println("Total logins: " + lg.length);
        System.out.println("Unique users (HashSet count): " + hs.size());
        System.out.println("\nFirst 3 distinct names (first-seen order):");
        int c = 0;
        for (String s : ls) {
            if (c >= 3) break;
            System.out.println("  " + (c + 1) + ". " + s);
            c++;
        }
    }
}
