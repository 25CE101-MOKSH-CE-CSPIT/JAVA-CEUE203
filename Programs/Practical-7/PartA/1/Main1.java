public class Main1 {
    public static void main(String[] args) throws Exception {
        SignupForm form = new SignupForm("", "verylongpassword123");
        for (String err : FormValidator.validate(form)) {
            System.out.println(err);
        }
    }
}
