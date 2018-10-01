package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.Resource;
import ea.engine.GameState;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ResourceServiceTest {

    private ResourceService target;
    private GameState gameState;

    @Before
    public void setup() {
        gameState = mock(GameState.class);
        target = new ResourceService(gameState);
    }

    @Test
    public void getPrice() {
        // When
        List<Resource> resources = ImmutableList.of(
                new Resource().withType(1).withPrice(1),
                new Resource().withType(1).withPrice(1),
                new Resource().withType(1).withPrice(1));

        // Act
        Integer actual = target.getPrice(resources,3);

        // Assert
        assertThat(actual).isEqualTo(3);
    }

}
