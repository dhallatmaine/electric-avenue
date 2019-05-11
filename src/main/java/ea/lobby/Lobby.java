package ea.lobby;

import java.util.List;
import java.util.UUID;

public class Lobby {

    private String id;
    private List<String> users;
    private UUID joinCode;

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

    public UUID getJoinCode() {
        return joinCode;
    }

    public Lobby withJoinCode(UUID joinCode) {
        this.joinCode = joinCode;
        return this;
    }
}
