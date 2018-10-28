package ea.maps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

class MapFactory {

    static List<City> getCityListFromJson(String jsonFilePath)
        throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
            new File(jsonFilePath),
            new TypeReference<List<City>>() { });
    }

}
