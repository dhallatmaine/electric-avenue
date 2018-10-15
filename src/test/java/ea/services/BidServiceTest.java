package ea.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import ea.data.*;
import ea.state.AuctionRound;
import ea.state.BidRound;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class BidServiceTest {

    PlayerService playerService;
    PowerPlantService powerPlantService;
    TurnOrderService turnOrderService;
    BidService target;
    Player player;
    State game;

    @Before
    public void setup() {
        playerService = mock(PlayerService.class);
        powerPlantService = mock(PowerPlantService.class);
        turnOrderService = mock(TurnOrderService.class);
        target = new BidService(playerService, powerPlantService, turnOrderService);

        player = new Player();
        game = new State()
                .withRound(1);
    }

    @Test
    @Parameters({
            " 5 | BLACK;BLUE;GREEN |             | false | Non passing bid       ",
            " 0 | BLACK;BLUE;GREEN | BLACK;GREEN | false | Passing bid           ",
            " 0 | BLUE             |             | true  | Passing bid end phase "
    })
    @TestCaseName("{4}")
    public void bid(
            Integer bidAmount,
            String orderStr,
            String passExpectedBidOrderStr,
            boolean phaseOver,
            String description) {
        // Arrange
        BidRequest request = new BidRequest()
                .withPlayer(Color.BLUE)
                .withBidAmount(bidAmount)
                .withPlantValue(5);

        List<Color> passExpectedBidOrder = Arrays.stream(passExpectedBidOrderStr.split(";"))
                .filter(str -> !str.isEmpty())
                .map(Color::valueOf)
                .collect(Collectors.toList());

        List<Color> order = Arrays.stream(orderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());
        BidRound bidRound = new BidRound()
                .withBidOrder(order);

        PowerPlant plant = new PowerPlant().withValue(5);
        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(plant);

        when(turnOrderService.determineOrderStartingAtPlayer(any(), any()))
                .thenReturn(ImmutableList.of(Color.BLUE, Color.GREEN, Color.BLACK));

        // Act
        BidResponse actual = target.bid(game, request);

        // Assert
        if (bidAmount > 0) {
            assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                    new BidResponse()
                            .withPlant(plant)
                            .withAuctionStarted(true));
            assertThat(bidRound).isEqualToComparingFieldByFieldRecursively(
                    new BidRound()
                            .withBidOrder(bidRound.getBidOrder())
                            .withPlantPurchased(true)
                            .withAuctionRounds(ImmutableMap.of(
                                    plant,
                                    new AuctionRound()
                                            .withBid(5)
                                            .withHighBidder(Color.BLUE)
                                            .withPlant(new PowerPlant().withValue(5))
                                            .withAuctionOrder(ImmutableList.of(Color.BLUE, Color.GREEN, Color.BLACK)))));
        } else {
            assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                    new BidResponse()
                            .withAuctionStarted(false)
                            .withPhaseOver(phaseOver));
            assertThat(bidRound).isEqualToComparingFieldByFieldRecursively(
                    new BidRound()
                            .withBidOrder(passExpectedBidOrder)
                            .withPlantPurchased(false));
        }
    }

    @Test
    @Parameters({
            " 5 | 0  | 5 | Insufficient funds                                   ",
            " 5 | 50 | 6 | Bid must be greater than or equal to the plant value "
    })
    @TestCaseName("{2}")
    public void validateBid(
            Integer bidAmount,
            Integer playerMoney,
            Integer plantValue,
            String description) {
        // Arrange
        BidRequest request = new BidRequest()
                .withBidAmount(bidAmount);

        when(playerService.findPlayerByColor(any(), any()))
                .thenReturn(player.withMoney(playerMoney));

        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(new PowerPlant().withValue(plantValue));

        // Act
        Throwable thrown = catchThrowable(() -> target.validateBid(game, request));

        // Assert
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage(description);
    }

}
