import java.util.regex.*;
public class TemplateFiller {
    public static void main(String[] a) {
        String t = "Dear {name}, order {id} ships {date}.";
        String[] n = {"name", "id"};
        String[] v = {"Riya", "A07"};
        Pattern p = Pattern.compile("\\{(\\w+)\\}");
        Matcher m = p.matcher(t);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String k = m.group(1);
            String rep = "[?]";
            for (int i=0; i<n.length; i++) {
                if (n[i].equals(k)) { rep = v[i]; break; }
            }
            m.appendReplacement(sb, rep);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
