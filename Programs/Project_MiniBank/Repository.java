import model.annotation.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository<T> {
    private Map<String, T> st = new HashMap<>();

    public T save(T entity) {
        if (entity == null) return null;
        String id = null;
        Class<?> cl = entity.getClass();
        while (cl != null && id == null) {
            for (Field f : cl.getDeclaredFields()) {
                if (f.isAnnotationPresent(Id.class)) {
                    f.setAccessible(true);
                    try {
                        Object val = f.get(entity);
                        if (val != null) id = val.toString();
                    } catch (IllegalAccessException e) {
                    }
                    break;
                }
            }
            cl = cl.getSuperclass();
        }

        if (id != null) {
            st.put(id, entity);
        }
        return entity;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T it : items) {
            save(it);
        }
    }

    public T findById(String id) {
        return st.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<>(st.values());
    }

    public boolean delete(String id) {
        return st.remove(id) != null;
    }

    public int count() {
        return st.size();
    }

    public static <N extends Number> double average(N[] vals) {
        if (vals == null || vals.length == 0) return 0.0;
        double sm = 0;
        for (N v : vals) {
            sm += v.doubleValue();
        }
        return sm / vals.length;
    }
}
