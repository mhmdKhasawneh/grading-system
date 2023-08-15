package server.models;
import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User(int id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public int getId() {
        return id;
    }
}
