package ea.lobby;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LobbyRepository extends MongoRepository<Lobby, String> {

}
