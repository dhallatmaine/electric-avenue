package ea.powerplant;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import ea.api.BidRequest;
import ea.api.BidResponse;
import ea.api.PassRequest;
import ea.player.Color;
import ea.player.Player;
import ea.player.PlayerService;
import ea.powerplant.PowerPlant;
import ea.game.GameService;
import ea.game.AuctionRound;
import ea.game.BidRound;
import ea.game.Game;
import ea.powerplant.BidService;
import ea.powerplant.PowerPlantService;
import ea.turnorder.TurnOrderService;
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
    GameService gameService;
    BidService target;
    Player player;
    Game game;

    @Before
    public void setup() {
        playerService = mock(PlayerService.class);
        powerPlantService = mock(PowerPlantService.class);
        turnOrderService = mock(TurnOrderService.class);
        gameService = mock(GameService.class);
        target = new BidService(playerService, powerPlantService, turnOrderService, gameService);

        player = new Player();
        game = new Game()
                .withRound(1);
    }

    @Test
    @Parameters({
            " BLACK;BLUE;GREEN | true  | More bidders ",
            " BLUE             | false | Last bidder  "
    })
    @TestCaseName("{2}")
    public void bid(
            String turnOrderStr,
            boolean startAuction,
            String description) {

        // Arrange
        BidRequest request = new BidRequest()
                .withPlayer(Color.BLUE)
                .withBidAmount(5)
                .withPlantValue(5);

        List<Color> order = Arrays.stream(turnOrderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());
        game.withTurnOrder(order);

        PowerPlant plant = new PowerPlant().withValue(5);
        when(powerPlantService.findPowerPlantInDeckByValue(any(), any()))
                .thenReturn(plant);

        when(turnOrderService.getNextPlayer(any(), any()))
                .thenReturn(Color.GREEN);
        List<Color> auctionOrder = ImmutableList.copyOf(order).reverse();
        when(turnOrderService.determineOrderStartingAtPlayer(any(), any()))
                .thenReturn(auctionOrder);

        // Act
        BidResponse actual = target.bid(game, request);

        // Assert
        BidRound bidRound = game.getBidRounds().get(game.getRound());
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                new BidResponse()
                        .withPlant(new PowerPlant().withValue(5))
                        .withPlayerToStartAuction(startAuction ? Color.GREEN : null)
                        .withPhaseOver(!startAuction)
                        .withOrder(order)
                        .withAuctionStarted(startAuction));
        assertThat(bidRound).isEqualToComparingFieldByFieldRecursively(
                new BidRound()
                        .withBidOrder(order)
                        .withPlantPurchased(true)
                        .withAuctionRounds(!startAuction ? ImmutableMap.of() : ImmutableMap.of(
                                5,
                                new AuctionRound()
                                        .withBid(5)
                                        .withHighBidder(Color.BLUE)
                                        .withAuctionFinished(false)
                                        .withPlant(new PowerPlant().withValue(5))
                                        .withAuctionOrder(auctionOrder))));
    }

    @Test
    @Parameters({
            " BLUE | BLACK;BLUE;GREEN | BLACK;GREEN | false | Passing bid           ",
            " BLUE | BLUE             |             | true  | Passing bid end phase "
    })
    @TestCaseName("{4}")
    public void pass(
            Color player,
            String orderStr,
            String expectedOrder,
            boolean phaseOver,
            String description) {
        // Arrange
        PassRequest request = new PassRequest().withPlayer(player);

        List<Color> order = Arrays.stream(orderStr.split(";"))
                .map(Color::valueOf)
                .collect(Collectors.toList());
        game.withTurnOrder(order);

        List<Color> passExpectedBidOrder = Arrays.stream(expectedOrder.split(";"))
                .filter(str -> !str.isEmpty())
                .map(Color::valueOf)
                .collect(Collectors.toList());

        // Act
        BidResponse actual = target.pass(game, request);

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(
                new BidResponse()
                        .withAuctionStarted(false)
                        .withOrder(passExpectedBidOrder)
                        .withPhaseOver(phaseOver));

        BidRound bidRound = game.getBidRounds().get(game.getRound());
        assertThat(bidRound).isEqualToComparingFieldByFieldRecursively(
                new BidRound()
                        .withBidOrder(passExpectedBidOrder)
                        .withPlantPurchased(false));
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
    public void validatePass() {
        // Arrange
        game.withRound(1);

        // Act
        Throwable thrown = catchThrowable(() -> target.validatePass(game));

        // Assert
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage("You must purchase a plant during the first round of the game");
    }

}
