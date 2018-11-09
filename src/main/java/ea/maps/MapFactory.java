package ea.maps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;

class MapFactory {

    static List<City> getCityListFromJson(String countryName) {

        ObjectMapper mapper = new ObjectMapper();

        String json;
        List<City> cityList;

        try {

            json = new String(
                ResourceUtils.getURL("classpath:" + countryName + ".json")
                    .openStream()
                    .readAllBytes()
            );

            cityList = mapper.readValue(json, new TypeReference<List<City>>() { });

        } catch (IOException ioe) {
            throw new IllegalStateException(
                "IOException caught: " + countryName + ".json cannot be found or read properly.");
        }

        return cityList;
    }
}
