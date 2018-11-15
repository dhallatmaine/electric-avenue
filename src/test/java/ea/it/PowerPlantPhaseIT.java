package ea.it;

import com.google.common.collect.ImmutableList;
import ea.api.AuctionResponse;
import ea.api.BidRequest;
import ea.api.BidResponse;
import ea.api.PassRequest;
import ea.controllers.PowerPlantBidController;
import ea.data.Color;
import ea.data.PowerPlant;
import ea.services.GameService;
import ea.state.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerPlantPhaseIT {

    @Autowired
    PowerPlantBidController powerPlantBidController;

    @Autowired
    GameService gameService;

    Game game;

    @Before
    public void setup() {
        game = gameService.createGame();
    }

    @Test(expected = RuntimeException.class)
    public void firstRoundPassThrowsError() {
        // Arrange
        PassRequest request = new PassRequest()
                .withPlayer(game.getCurrentTurn());

        // Act
        powerPlantBidController.pass(game.getGameId(), request);

        // Assert
    }

    @Test
    public void firstRound() {
        //Arrange

        // Act
        BidResponse initialBidResponse = powerPlantBidController.bid(
                game.getGameId(),
                new BidRequest()
                        .withPlayer(game.getCurrentTurn())
                        .withPlantValue(3)
                        .withBidAmount(3));

        AuctionResponse initialAuctionResponse = powerPlantBidController.auction(
                game.getGameId(),
                new BidRequest()
                        .withPlayer(initialBidResponse.getPlayerToStartAuction())
                        .withPlantValue(3)
                        .withBidAmount(4));

        AuctionResponse passAuctionResponse = powerPlantBidController.auctionPass(
                game.getGameId(),
                initialAuctionResponse.getPlant().getValue(),
                new PassRequest()
                        .withPlayer(initialAuctionResponse.getNextBidder()));

        Color winningPlayer = passAuctionResponse.getNextBidder();
        AuctionResponse winningAuctionResponse = powerPlantBidController.auction(
                game.getGameId(),
                new BidRequest()
                        .withPlayer(winningPlayer)
                        .withPlantValue(3)
                        .withBidAmount(5));

        AuctionResponse passToEndAuctionResponse = powerPlantBidController.auctionPass(
                game.getGameId(),
                winningAuctionResponse.getPlant().getValue(),
                new PassRequest()
                        .withPlayer(winningAuctionResponse.getNextBidder()));

        // Assert
        assertThat(passToEndAuctionResponse).isEqualToComparingFieldByFieldRecursively(
                new AuctionResponse()
                        .withPlant(passAuctionResponse.getPlant())
                        .withAuctionFinished(true)
                        .withHighBid(5)
                        .withHighBidder(winningPlayer)
                        .withNextBidder(null)
                        .withOrder(ImmutableList.of(winningPlayer)));

        // make sure the winner of the auction is removed from the bid turn order
        assertThat(game.getBidRounds().get(1).getBidOrder()).doesNotContain(winningPlayer);

        // capture plant
        assertThat(game.getCurrentMarketPlants()).contains(new PowerPlant().withValue(3));
    }

}
