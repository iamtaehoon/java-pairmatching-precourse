package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void 백엔드_코스_생성_정상() {
        Course backend = Course.find("백엔드");
        assertThat(backend).isEqualTo(Course.BACKEND);
    }

    @Test
    void 프론트엔드_코스_생성_정상() {
        Course frontend = Course.find("프론트엔드");
        assertThat(frontend).isEqualTo(Course.FRONTEND);
    }
}