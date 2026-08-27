public class User {
    @Column(name = "id")
    public String id;
    
    @Column(name = "full_name")
    public String name;

    @Override
    public String toString() { return "User{id=" + id + ", name=" + name + "}"; }
}
