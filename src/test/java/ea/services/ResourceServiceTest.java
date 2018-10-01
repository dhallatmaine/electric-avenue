package ea.services;

import com.google.common.collect.ImmutableList;
import ea.data.Resource;
import ea.engine.GameState;
import org.junit.Before;
import org.junit.Test;

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


        // Act
        Integer actual = target.getPrice(ImmutableList.of(
            new Resource().withType(1).withPrice(1),
            new Resource().withType(1).withPrice(1),
            new Resource().withType(1).withPrice(1)
        ),
        3);
        // Assert
        assertThat(actual).isEqualTo(3);

        /*
        int price = 0;

    Iterator itr = resources.iterator();
    while (itr.hasNext() && amount > 0) {
      Resource r = (Resource) itr.next();
      price += r.getPrice();
      amount--;
    }

    return price;
         */
    }

}
