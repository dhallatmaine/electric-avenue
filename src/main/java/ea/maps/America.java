package ea.maps;

import com.google.common.collect.ImmutableList;
import ea.data.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class America extends BaseMap {

    public void initializeCityList() throws IOException {
        cities = MapFactory.getCityListFromJson("america");
    }

    public static Map<Resource, List<Integer>> initializeResources() {
        Map<Resource, List<Integer>> built = new HashMap<>();

        built.put(Resource.COAL, ImmutableList.of(
            1, 1, 1,
            2, 2, 2,
            3, 3, 3,
            4, 4, 4,
            5, 5, 5,
            6, 6, 6,
            7, 7, 7,
            8, 8, 8));

        built.put(Resource.OIL, ImmutableList.of(
            0, 0, 0,
            0, 0, 0,
            3, 3, 3,
            4, 4, 4,
            5, 5, 5,
            6, 6, 6,
            7, 7, 7,
            8, 8, 8));

        built.put(Resource.TRASH, ImmutableList.of(
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            0, 0, 0,
            7, 7, 7,
            8, 8, 8));

        built.put(Resource.URANIUM, ImmutableList.of(
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            14,
            16));

        return built;
    }

}
