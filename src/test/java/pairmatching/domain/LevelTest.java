package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LevelTest {

    @Test
    void 레벨안_미션_있음_정상() {
        Level.find("레벨1").hasMission(Mission.NUMBER_BASEBALL);
    }
}