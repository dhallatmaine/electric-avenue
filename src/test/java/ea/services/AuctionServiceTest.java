package ea.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import ea.api.AuctionResponse;
import ea.api.BidRequest;
import ea.api.PassRequest;
import ea.data.Color;
import ea.data.Player;
import ea.data.PowerPlant;
import ea.state.AuctionRound;
import ea.state.BidRound;
import ea.state.Game;
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
public class AuctionServiceTest {

    PowerPlantService powerPlantService;
    TurnOrderService turnOrderService;
    PlayerService playerService;
    AuctionService target;

    Game game;
    Player player;

    @Before
    public void setup() {
        powerPlantService = mock(PowerPlantService.class);
        turnOrderService = mock(TurnOrderService.class);
        playerService = mock(PlayerService.class);
        target = new AuctionService(powerPlantService, turnOrderService, playerService);

        player = new Player();
        game = new Game()
                .withRound(1);
    }

    @Test
    public void auction() {
        // Arrange
        BidRequest bid = new BidRequest()
                .withPlantValue(5)
                .withPlayer(Color.BLUE)
                .withBidAmount(6);

        AuctionRound auctionRound = new AuctionRound()
                .withAuctionOrder(ImmutableList.of(Color.BLUE, Color.BLACK, Color.GREEN))
                .withBid(5)
                .withHighBidder(Color.GREEN)
                .withPlant(new PowerPlant().withValue(5));

        game.withBidRounds(ImmutableMap.of(1, new BidRound()
                .withAuctionRounds(ImmutableMap.of(
                        new PowerPlant().withValue(5), auctionRound))));

        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(new PowerPlant().withValue(5));

        when(turnOrderService.getNextPlayer(any(), any()))
                .thenReturn(Color.BLACK);

        // Act
        AuctionResponse actual = target.auction(game, bid);

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                new AuctionResponse()
                        .withHighBidder(Color.BLUE)
                        .withHighBid(6)
                        .withNextBidder(Color.BLACK)
                        .withOrder(ImmutableList.of(Color.BLUE, Color.BLACK, Color.GREEN))
                        .withPlant(new PowerPlant().withValue(5))
                        .withAuctionFinished(false));
    }

    @Test
    @Parameters({
            " BLUE | BLUE;BLACK;GREEN | BLACK;GREEN | false | Passing auction ",
            " BLUE | BLUE;BLACK       | BLACK       | true  | End auction     "
    })
    @TestCaseName("{4}")
    public void pass(
            Color player,
            String orderStr,
            String expectedOrderStr,
            boolean phaseOver,
            String description) {
        // Arrange
        PassRequest pass = new PassRequest()
                .withPlayer(player);

        List<Color> order = Arrays.stream(orderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());

        AuctionRound auctionRound = new AuctionRound()
                .withAuctionOrder(order)
                .withBid(5)
                .withHighBidder(Color.GREEN)
                .withPlant(new PowerPlant().withValue(5));
        BidRound bidRound = new BidRound()
                .withBidOrder(order)
                .withAuctionRounds(ImmutableMap.of(
                        new PowerPlant().withValue(5), auctionRound));

        game.withBidRounds(ImmutableMap.of(1, bidRound));

        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(new PowerPlant().withValue(5));

        when(turnOrderService.getNextPlayer(any(), any()))
                .thenReturn(Color.BLACK);

        // Act
        AuctionResponse actual = target.pass(game, pass, 5);

        // Assert
        List<Color> expectedOrder = Arrays.stream(expectedOrderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());
        if (phaseOver) {
            assertThat(game.getBidRounds().get(1).getBidOrder())
                    .doesNotContain(auctionRound.getHighBidder());
        }
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                new AuctionResponse()
                        .withHighBidder(Color.GREEN)
                        .withHighBid(5)
                        .withAuctionFinished(false)
                        .withNextBidder(phaseOver ? null : Color.BLACK)
                        .withOrder(expectedOrder)
                        .withPlant(new PowerPlant().withValue(5))
                        .withAuctionFinished(phaseOver));
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
