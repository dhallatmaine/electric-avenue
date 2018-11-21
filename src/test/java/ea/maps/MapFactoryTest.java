package ea.maps;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class MapFactoryTest {

    private List<City> americaCities;

    @Before
    public void init() {
        americaCities = MapFactory.getCityListFromJson("america");
    }

    @Test
    public void allCitiesHaveConnectionTest() {
        americaCities.forEach(c -> assertTrue(c.getConnectedCities().size() > 0));
    }

    @Test
    public void allConnectionsCityNamesCorrespondToExistingCity() {

        Set<String> connectionCityNames = americaCities
            .stream()
            .flatMap(ac -> ac.getConnectedCities().stream())
            .map(Connection::getCityName)
            .collect(Collectors.toSet());

        Set<String> cityNames = americaCities
            .stream()
            .map(City::getName)
            .collect(Collectors.toSet());

        assertThat(cityNames).isEqualTo(connectionCityNames);
    }
}
