public class SignupForm {
    @NotBlank
    @MaxLength(10)
    public String username;

    @NotBlank
    public String password;

    public SignupForm(String u, String p) { username = u; password = p; }
}
