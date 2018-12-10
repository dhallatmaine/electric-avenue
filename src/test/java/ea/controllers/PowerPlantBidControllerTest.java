package ea.controllers;

import ea.api.*;
import ea.data.*;
import ea.services.AuctionService;
import ea.services.BidService;
import ea.services.GameService;
import ea.state.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PowerPlantBidControllerTest {

    GameService gameService;
    BidService bidService;
    AuctionService auctionService;
    PowerPlantBidController target;

    Game game;
    String gameId = "1";

    @Before
    public void setup() {
        gameService = mock(GameService.class);
        bidService = mock(BidService.class);
        auctionService = mock(AuctionService.class);
        target = new PowerPlantBidController(
                gameService,
                bidService,
                auctionService);

        game = new Game().withRound(1);
    }

    @Test
    public void bid() {
        // Arrange
        when(gameService.getGame(any()))
                .thenReturn(Optional.of(game));

        BidResponse bidResponse = new BidResponse()
                .withPlant(new PowerPlant().withValue(5));
        when(bidService.bid(any(), any()))
                .thenReturn(bidResponse);

        // Act
        BidResponse actual = target.bid(gameId, new BidRequest());

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(bidResponse);
    }

    @Test
    public void pass() {
        // Arrange
        when(gameService.getGame(any()))
                .thenReturn(Optional.of(game));

        BidResponse bidResponse = new BidResponse()
                .withPlant(new PowerPlant().withValue(5));
        when(bidService.pass(any(), any()))
                .thenReturn(bidResponse);

        // Act
        BidResponse actual = target.pass(gameId, new PassRequest());

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(bidResponse);
    }

    @Test
    public void auction() {
        // Arrange
        when(gameService.getGame(any()))
                .thenReturn(Optional.of(game));

        AuctionResponse auctionResponse = new AuctionResponse()
                .withPlant(new PowerPlant().withValue(5));
        when(auctionService.auction(any(), any()))
                .thenReturn(auctionResponse);

        // Act
        AuctionResponse actual = target.auction(gameId, new BidRequest());

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(auctionResponse);
    }

    @Test
    public void auctionPass() {
        // Arrange
        when(gameService.getGame(any()))
                .thenReturn(Optional.of(game));

        AuctionResponse auctionResponse = new AuctionResponse()
                .withPlant(new PowerPlant().withValue(5));
        when(auctionService.pass(any(), any(), any()))
                .thenReturn(auctionResponse);

        // Act
        AuctionResponse actual = target.auctionPass(gameId, 1, new PassRequest());

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(auctionResponse);
    }

    @Test
    public void capture() {
        // Arrange
        when(gameService.getGame(any()))
                .thenReturn(Optional.of(game));

        PlantCaptureResponse plantCaptureResponse = new PlantCaptureResponse()
                .withPlant(new PowerPlant().withValue(5));
        when(bidService.capture(any(), any()))
                .thenReturn(plantCaptureResponse);

        // Act
        PlantCaptureResponse actual = target.capture(gameId, new PlantCaptureRequest());

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(plantCaptureResponse);
    }

}
