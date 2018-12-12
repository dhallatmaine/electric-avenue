package ea.it;

import com.google.common.collect.ImmutableList;
import ea.api.*;
import ea.controllers.PowerPlantBidController;
import ea.data.Color;
import ea.data.PowerPlant;
import ea.services.GameService;
import ea.services.PlayerService;
import ea.state.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerPlantPhaseIT {

    @Autowired
    PowerPlantBidController powerPlantBidController;

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

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
        game = gameService.getGame(game.getGameId()).get();
        assertThat(game.getBidRounds().get(1).getBidOrder()).doesNotContain(winningPlayer);

        // capture first plant
        PowerPlant firstPlant = winningAuctionResponse.getPlant();
        powerPlantBidController.capture(
                game.getGameId(),
                new PlantCaptureRequest()
                        .withPlant(firstPlant.getValue())
                        .withPlayer(winningPlayer));

        game = gameService.getGame(game.getGameId()).get();
        assertThat(game.getCurrentMarketPlants()).doesNotContain(firstPlant);
        assertThat(playerService.findPlayerByColor(game, winningPlayer).getPowerPlants()).contains(firstPlant);

        /////////////////////////////////
        // Second bidding / auction phase
        Color secondWinningPlayer = game.getBidRounds().get(1).getBidOrder().get(0);
        BidResponse secondBidResponse = powerPlantBidController.bid(game.getGameId(),
                new BidRequest()
                        .withPlayer(secondWinningPlayer)
                        .withBidAmount(4)
                        .withPlantValue(4));

        AuctionResponse passToEndSecondAuctionResponse = powerPlantBidController.auctionPass(
                game.getGameId(),
                secondBidResponse.getPlant().getValue(),
                new PassRequest().withPlayer(secondBidResponse.getPlayerToStartAuction()));

        assertThat(passToEndSecondAuctionResponse).isEqualToComparingFieldByFieldRecursively(
                new AuctionResponse()
                        .withPlant(passToEndSecondAuctionResponse.getPlant())
                        .withAuctionFinished(true)
                        .withHighBid(4)
                        .withHighBidder(secondWinningPlayer)
                        .withNextBidder(null)
                        .withOrder(ImmutableList.of(secondWinningPlayer)));

        game = gameService.getGame(game.getGameId()).get();
        assertThat(game.getBidRounds().get(1).getBidOrder()).doesNotContain(winningPlayer, secondWinningPlayer);

        // capture plant
        PowerPlant secondPlant = passToEndSecondAuctionResponse.getPlant();
        powerPlantBidController.capture(
                game.getGameId(),
                new PlantCaptureRequest()
                        .withPlant(secondPlant.getValue())
                        .withPlayer(secondWinningPlayer));

        game = gameService.getGame(game.getGameId()).get();
        assertThat(game.getCurrentMarketPlants()).doesNotContain(secondPlant);
        assertThat(playerService.findPlayerByColor(game, secondWinningPlayer).getPowerPlants()).contains(secondPlant);

        /////////////////////////////////
        // Last bid / no auction
        Color lastWinningPlayer = game.getBidRounds().get(1).getBidOrder().get(0);
        BidResponse lastBidResponse = powerPlantBidController.bid(game.getGameId(),
                new BidRequest()
                        .withPlayer(lastWinningPlayer)
                        .withBidAmount(5)
                        .withPlantValue(5));

        game = gameService.getGame(game.getGameId()).get();
        assertThat(lastBidResponse.getPhaseOver()).isTrue();
        assertThat(lastBidResponse.getAuctionStarted()).isFalse();
        assertThat(game.getPhase()).isEqualToIgnoringCase("ResourcePhase");

        // capture plant
        PowerPlant lastPlant = lastBidResponse.getPlant();
        powerPlantBidController.capture(
                game.getGameId(),
                new PlantCaptureRequest()
                        .withPlant(lastPlant.getValue())
                        .withPlayer(lastWinningPlayer));

        game = gameService.getGame(game.getGameId()).get();
        assertThat(game.getCurrentMarketPlants()).doesNotContain(lastPlant);
        assertThat(playerService.findPlayerByColor(game, lastWinningPlayer).getPowerPlants()).contains(lastPlant);
    }

}
