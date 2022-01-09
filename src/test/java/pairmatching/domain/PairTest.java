package pairmatching.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PairTest {

    @Test
    void 페어_조회_정상() {
        Crew a = new Crew(Course.BACKEND, "A");
        Crew b = new Crew(Course.BACKEND, "B");
        Pair pair = new Pair(Arrays.asList(a, b));

        if (pair.hasCrew(a)) {
            Assertions.assertThat(pair.getHisPair(a)).contains(b);
            Assertions.assertThat(pair.getHisPair(a)).doesNotContain(a);
        }
    }

    @Test
    void 페어_조회_다른페어() {
        Crew a = new Crew(Course.BACKEND, "A");
        Crew b = new Crew(Course.BACKEND, "B");
        Crew c = new Crew(Course.BACKEND, "C");
        Pair pair = new Pair(Arrays.asList(a, b));

        if (pair.hasCrew(a)) {
            Assertions.assertThat(pair.getHisPair(a)).doesNotContain(c);
        }
    }
}