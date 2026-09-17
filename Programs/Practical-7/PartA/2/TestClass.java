public class TestClass {
    @Run
    public void test1() { System.out.println("Running test 1"); }
    public void notATest() { System.out.println("Should not run"); }
    @Run
    public void test2() { System.out.println("Running test 2"); }
}
