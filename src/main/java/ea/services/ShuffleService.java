package ea.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ShuffleService {

    public <T> List<T> shuffle(List<T> toShuffle) {
        List<T> copy = new ArrayList<>(toShuffle);
        Collections.shuffle(copy);
        return copy;
    }

}
