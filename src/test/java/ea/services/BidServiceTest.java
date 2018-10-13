package ea.services;

import ea.data.BidRequest;
import ea.data.Player;
import ea.state.State;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class BidServiceTest {

    PlayerService playerService;
    PowerPlantService powerPlantService;
    BidService target;

    @Before
    public void setup() {
        playerService = mock(PlayerService.class);
        powerPlantService = mock(PowerPlantService.class);
        target = new BidService(playerService, powerPlantService);
    }

    @Test
    @Parameters({
            " 5 | 0  | 0 | Insufficient funds           ",
            " 5 | 50 | 6 | Bid is less than current bid "
    })
    @TestCaseName("{2}")
    public void validateBid(
            Integer bidAmount,
            Integer playerMoney,
            Integer currentBidAmount,
            String description) {
        // Arrange
        BidRequest request = new BidRequest()
                .withBidAmount(bidAmount);

        State game = new State()
                .withCurrentBid(currentBidAmount);

        when(playerService.findPlayerByColor(eq(game), any()))
                .thenReturn(new Player().withMoney(playerMoney));

        // Act
        Throwable thrown = catchThrowable(() -> target.validateBid(game, request));

        // Assert
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage(description);
    }

}
