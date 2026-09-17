import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormValidator {
    public static List<String> validate(Object obj) throws Exception {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            Object val = f.get(obj);
            if (f.isAnnotationPresent(NotBlank.class)) {
                if (val == null || val.toString().trim().isEmpty()) {
                    errors.add(f.getName() + " cannot be blank.");
                }
            }
            if (f.isAnnotationPresent(MaxLength.class)) {
                int max = f.getAnnotation(MaxLength.class).value();
                if (val != null && val.toString().length() > max) {
                    errors.add(f.getName() + " exceeds max length of " + max + ".");
                }
            }
        }
        return errors;
    }
}
