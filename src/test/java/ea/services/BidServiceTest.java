package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.*;
import ea.state.State;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class BidServiceTest {

    PlayerService playerService;
    PowerPlantService powerPlantService;
    BidService target;
    Player player;
    State game;

    @Before
    public void setup() {
        playerService = mock(PlayerService.class);
        powerPlantService = mock(PowerPlantService.class);
        target = new BidService(playerService, powerPlantService);

        player = new Player();
        game = new State();
    }

    @Test
    @Parameters({
            " 5 | BLUE;PURPLE;BLACK | BLUE;PURPLE;BLACK | false | Non passing bid amount    ",
            " 0 | BLUE;PURPLE;BLACK | PURPLE;BLACK      | false | Pass and continue bidding ",
            " 0 | BLUE;PURPLE       | PURPLE            | true  | Pass and end bidding      "
    })
    @TestCaseName("{4}")
    public void createBid(
            Integer bidAmount,
            String bidOrderStr,
            String expectedBidOrderStr,
            boolean biddingEnded,
            String description) {
        // Arrange
        BidRequest request = new BidRequest()
                .withBidAmount(bidAmount)
                .withPlayer(Color.BLUE);

        List<Color> bidOrder = Arrays.stream(bidOrderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());
        game.withBidOrder(bidOrder);

        List<Color> expectedBidOrder = Arrays.stream(expectedBidOrderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());

        when(playerService.findPlayerByColor(any(), any()))
                .thenReturn(player.withMoney(50).withColor(Color.BLUE));

        //when(powerPlantService.findPowerPlantInDeckByValue(game.getDeckPlants(), bid.getPlantValue()))

        // Act
        BidResponse actual = target.createBid(game, request);

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                new BidResponse()
                        .withBid(request)
                        .withBiddingEnded(biddingEnded)
                        .withNextPlayer(Color.PURPLE)
                        .withCurrentBid(bidAmount));

        if (!biddingEnded)
            assertThat(game.getBidOrder()).isEqualTo(expectedBidOrder);
        //else
            //verify(playerService).capturePlant(game, );
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

        game.withCurrentBid(currentBidAmount);

        when(playerService.findPlayerByColor(eq(game), any()))
                .thenReturn(player.withMoney(playerMoney));

        // Act
        Throwable thrown = catchThrowable(() -> target.validateBid(game, request));

        // Assert
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage(description);
    }

}
