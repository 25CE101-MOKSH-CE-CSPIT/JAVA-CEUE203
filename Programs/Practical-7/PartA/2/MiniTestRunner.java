import java.lang.reflect.Method;

public class MiniTestRunner {
    public static void main(String[] args) throws Exception {
        TestClass obj = new TestClass();
        int ran = 0;
        for (Method m : TestClass.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Run.class)) {
                m.invoke(obj);
                ran++;
            }
        }
        System.out.println("Tests run: " + ran);
    }
}
