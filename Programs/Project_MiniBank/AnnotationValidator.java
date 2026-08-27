import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.annotation.Positive;
import model.annotation.MaxLength;

public class AnnotationValidator {
    public static String[] validate(Object obj) {
        List<String> errors = new ArrayList<>();
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.isAnnotationPresent(Positive.class)) {
                    Object val = f.get(obj);
                    if (val instanceof Number n) {
                        if (n.doubleValue() <= 0) {
                            errors.add(f.getName() + " " + f.getAnnotation(Positive.class).message());
                        }
                    }
                }
                if (f.isAnnotationPresent(MaxLength.class)) {
                    Object val = f.get(obj);
                    int max = f.getAnnotation(MaxLength.class).value();
                    if (val != null && val.toString().length() > max) {
                        errors.add(f.getName() + " exceeds max length of " + max);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errors.toArray(new String[0]);
    }
}
