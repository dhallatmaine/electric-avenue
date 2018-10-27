package ea.maps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

class MapFactory {

    private static List<City> getCityListFromJson(String jsonFilePath)
        throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
            new File(jsonFilePath),
            new TypeReference<List<City>>() { });
    }


    public static void main(String[] args)
        throws IOException {

        List<City> americanCities = getCityListFromJson("src\\main\\resources\\america.json");

        for (City city : americanCities) {
            System.out.println(city);
        }
    }
}
