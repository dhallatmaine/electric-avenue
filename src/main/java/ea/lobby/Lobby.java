package ea.lobby;

import java.util.List;

public class Lobby {

    private String id;
    private List<String> users;
    private String joinCode;

    public String getId() {
        return id;
    }

    public Lobby withId(String id) {
        this.id = id;
        return this;
    }

    public List<String> getUsers() {
        return users;
    }

    public Lobby withUsers(List<String> users) {
        this.users = users;
        return this;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public Lobby withJoinCode(String joinCode) {
        this.joinCode = joinCode;
        return this;
    }
}
