package ea.game;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException() {
        super("Game was not found");
    }

}
