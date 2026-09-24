import java.util.*;

public class WordFrequency {
    public static void main(String[] a) {
        String p = "java is great java is fun python is great java is best fun is great";
        String[] w = p.split("\\s+");
        HashMap<String, Integer> m = new HashMap<>();
        for (String s : w) {
            m.put(s, m.getOrDefault(s, 0) + 1);
        }
        List<Map.Entry<String, Integer>> l = new ArrayList<>(m.entrySet());
        l.sort((e1, e2) -> e2.getValue() - e1.getValue());
        System.out.println("Word Frequency (sorted by frequency - highest first):");
        System.out.println("-----------------------------------------------------");
        for (Map.Entry<String, Integer> e : l) {
            System.out.printf("%-10s : %d%n", e.getKey(), e.getValue());
        }
    }
}
