package change.com.animationwithsplash.Models;

public class User {
    private String id;//always keep the names of class attributes same as database
    private String Username;

    public User(String id, String username) {
        this.id = id;
        this.Username = username;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }
}
