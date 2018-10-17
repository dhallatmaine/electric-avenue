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
            " 5 | BLACK;BLUE;GREEN | GREEN;BLACK;BLUE |             | false | Non passing bid       ",
            " 0 | BLACK;BLUE;GREEN | GREEN;BLACK;BLUE | BLACK;GREEN | false | Passing bid           ",
            " 0 | BLUE             | BLUE             |             | true  | Passing bid end phase "
    })
    @TestCaseName("{5}")
    public void bid(
            Integer bidAmount,
            String orderStr,
            String auctionOrderStr,
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
        game.withTurnOrder(order);

        PowerPlant plant = new PowerPlant().withValue(5);
        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(plant);

        when(turnOrderService.getNextPlayer(any(), any()))
                .thenReturn(Color.GREEN);
        List<Color> auctionOrder = Arrays.stream(auctionOrderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());
        when(turnOrderService.determineOrderStartingAtPlayer(any(), any()))
                .thenReturn(auctionOrder);

        // Act
        BidResponse actual = target.bid(game, request);

        // Assert
        BidRound bidRound = game.getBidRounds().get(game.getRound());
        if (bidAmount > 0) {
            assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                    new BidResponse()
                            .withPlant(new PowerPlant().withValue(5))
                            .withPlayerToStartAuction(Color.GREEN)
                            .withPhaseOver(false)
                            .withOrder(order)
                            .withAuctionStarted(true));
            assertThat(bidRound).isEqualToComparingFieldByFieldRecursively(
                    new BidRound()
                            .withBidOrder(order)
                            .withPlantPurchased(true)
                            .withAuctionRounds(ImmutableMap.of(
                                    new PowerPlant().withValue(5),
                                    new AuctionRound()
                                            .withBid(5)
                                            .withHighBidder(Color.BLUE)
                                            .withAuctionFinished(false)
                                            .withPlant(new PowerPlant().withValue(5))
                                            .withAuctionOrder(ImmutableList.of(
                                                    Color.GREEN, Color.BLACK, Color.BLUE)))));
        } else {
            assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                    new BidResponse()
                            .withAuctionStarted(false)
                            .withOrder(passExpectedBidOrder)
                            .withPhaseOver(phaseOver));
            assertThat(bidRound).isEqualToComparingFieldByFieldRecursively(
                    new BidRound()
                            .withBidOrder(passExpectedBidOrder)
                            .withPlantPurchased(false));
        }
    }

    @Test
    @Parameters({
            " 5 | 0  | 5 | BLUE;GREEN | BLUE  | Insufficient funds                                   ",
            " 5 | 50 | 6 | BLUE;GREEN | BLUE  | Bid must be greater than or equal to the plant value ",
            " 5 | 50 | 5 | BLUE;GREEN | BLACK | Player is not eligible to bid                        "
    })
    @TestCaseName("{5}")
    public void validateBid(
            Integer bidAmount,
            Integer playerMoney,
            Integer plantValue,
            String orderStr,
            Color playerColor,
            String description) {
        // Arrange
        BidRequest request = new BidRequest()
                .withBidAmount(bidAmount)
                .withPlayer(playerColor);

        when(playerService.findPlayerByColor(any(), any()))
                .thenReturn(player.withMoney(playerMoney));

        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(new PowerPlant().withValue(plantValue));

        List<Color> order = Arrays.stream(orderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());
        game.withRound(1).withBidRounds(
                ImmutableMap.of(1, new BidRound().withBidOrder(order)));

        // Act
        Throwable thrown = catchThrowable(() -> target.validateBid(game, request));

        // Assert
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage(description);
    }

    @Test
    @Parameters({
            " 5 | 0  | 0 | BLUE;GREEN | BLUE  | Insufficient funds                        ",
            " 5 | 50 | 6 | BLUE;GREEN | BLUE  | Bid must be greater than current high bid ",
            " 5 | 50 | 0 | BLUE;GREEN | BLACK | Player is not eligible to bid             "
    })
    @TestCaseName("{5}")
    public void validateAuction(
            Integer bidAmount,
            Integer playerMoney,
            Integer currentBid,
            String orderStr,
            Color playerColor,
            String description) {
        // Arrange
        BidRequest request = new BidRequest()
                .withBidAmount(bidAmount)
                .withPlayer(playerColor);

        when(playerService.findPlayerByColor(any(), any()))
                .thenReturn(player.withMoney(playerMoney));

        PowerPlant plant = new PowerPlant().withValue(5);
        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(plant);

        List<Color> order = Arrays.stream(orderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());

        game.withRound(1).withBidRounds(
                ImmutableMap.of(1, new BidRound().withAuctionRounds(ImmutableMap.of(
                        plant,
                        new AuctionRound().withAuctionOrder(order).withBid(currentBid)))));

        // Act
        Throwable thrown = catchThrowable(() -> target.validateAuction(game, request));

        // Assert
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage(description);
    }

}
