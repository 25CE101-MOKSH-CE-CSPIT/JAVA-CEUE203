import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CsvMapper {
    public static <T> T map(String[] headers, String[] data, Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        Map<String, String> row = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            row.put(headers[i], i < data.length ? data[i] : null);
        }
        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(Column.class)) {
                String colName = f.getAnnotation(Column.class).name();
                if (row.containsKey(colName)) {
                    f.setAccessible(true);
                    f.set(obj, row.get(colName));
                }
            }
        }
        return obj;
    }
}
