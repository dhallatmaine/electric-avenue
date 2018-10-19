package ea.controllers;

import ea.data.*;
import ea.services.BidService;
import ea.services.GameService;
import ea.state.State;
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
    PowerPlantBidController target;

    State game;

    @Before
    public void setup() {
        gameService = mock(GameService.class);
        bidService = mock(BidService.class);
        target = new PowerPlantBidController(gameService, bidService);

        game = new State().withRound(1);
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
        BidResponse actual = target.bid(1, new BidRequest());

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
        BidResponse actual = target.pass(1, new PassRequest());

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
        when(bidService.auction(any(), any()))
                .thenReturn(auctionResponse);

        // Act
        AuctionResponse actual = target.auction(1, new BidRequest());

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
        when(bidService.pass(any(), any(), any()))
                .thenReturn(auctionResponse);

        // Act
        AuctionResponse actual = target.auctionPass(1, 1, new PassRequest());

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
        PlantCaptureResponse actual = target.capture(1, new PlantCaptureRequest());

        // Assert
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(plantCaptureResponse);
    }

}
