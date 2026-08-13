public class ChatFilter {
    public static void main(String[] a) {
        String[] logs = {"10:05 alice Hello there", "10:06 bob Hi", "10:07 invalid_log"};
        String k = "hello";
        int m = 0;
        StringBuilder sb = new StringBuilder();
        for (String l : logs) {
            String[] p = l.split(" ", 3);
            if (p.length < 3) continue;
            if (p[2].toLowerCase().contains(k.toLowerCase())) {
                m++;
                sb.append(p[0]).append(" ").append(p[1]).append(": ").append(p[2]).append("\n");
            }
        }
        System.out.println("Matches: " + m);
        System.out.println(sb.toString());
    }
}
