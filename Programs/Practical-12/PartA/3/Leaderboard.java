import java.util.*;

public class Leaderboard {
    public static void main(String[] a) {
        Map<String, Integer> m = new HashMap<>();
        m.put("Alice", 950);
        m.put("Bob", 1200);
        m.put("Charlie", 850);
        m.put("Dave", 1200);
        m.put("Eve", 1100);
        m.put("Frank", 950);
        m.put("Grace", 1300);
        int n = 5;
        List<Map.Entry<String, Integer>> l = new ArrayList<>(m.entrySet());
        l.sort((e1, e2) -> {
            int d = e2.getValue() - e1.getValue();
            if (d != 0) return d;
            return e1.getKey().compareTo(e2.getKey());
        });
        System.out.println("=== Leaderboard (Top " + n + ") ===");
        System.out.printf("%-6s %-10s %s%n", "Rank", "Player", "Score");
        System.out.println("----------------------------");
        for (int i = 0; i < Math.min(n, l.size()); i++) {
            Map.Entry<String, Integer> e = l.get(i);
            System.out.printf("%-6d %-10s %d%n", i + 1, e.getKey(), e.getValue());
        }
    }
}
