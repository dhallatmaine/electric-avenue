package ea.it;

import ea.api.PassRequest;
import ea.controllers.PowerPlantBidController;
import ea.services.GameService;
import ea.state.State;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerPlantPhaseIT {

    @Autowired
    PowerPlantBidController powerPlantBidController;

    @Autowired
    GameService gameService;

    State game;

    @Before
    public void setup() {
        game = gameService.createGame();
    }

    @Test(expected = RuntimeException.class)
    public void firstRoundPassThrowsError() {
        PassRequest request = new PassRequest()
                .withPlayer(game.getCurrentTurn());

        powerPlantBidController.pass(game.getGameId(), request);
    }

}
