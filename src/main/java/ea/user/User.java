package ea.user;

import org.springframework.data.annotation.Id;

import java.util.List;

public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private List<String> gameIds;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public List<String> getGameIds() {
        return gameIds;
    }

    public User withGameIds(List<String> gameIds) {
        this.gameIds = gameIds;
        return this;
    }

}
