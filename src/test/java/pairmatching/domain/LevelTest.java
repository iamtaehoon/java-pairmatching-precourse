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

    @Test
    void 레벨안_미션_없음_예외() {
        assertThatThrownBy(Level.find("레벨2").hasMission(Mission.DISTRIBUTE)).isInstanceOf(
            IllegalArgumentException.class).hasMessageContaining("이 레벨에는 해당 미션이 존재하지 않습니다.");
    }

    @Test
    void 없는_미션_예외() {
        assertThatThrownBy(Level.find("레벨2").hasMission(null)).isInstanceOf(
            IllegalArgumentException.class).hasMessageContaining("해당 미션은 존재하지 않습니다.");
    }

}