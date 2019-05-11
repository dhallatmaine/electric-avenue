package ea.lobby;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LobbyRepository extends MongoRepository<Lobby, String> {

    Lobby findByJoinCode(UUID joinCode);

}
