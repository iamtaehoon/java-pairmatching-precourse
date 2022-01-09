package pairmatching.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import pairmatching.util.CrewConvertor;

class PairsTest { // TODO 전부 끝나고 다시 테스트를 제대로 만들어봐야겠다. 이 두개 다 테스트라고 볼 수도 없음.

    @Test
    void 페어쌍_생성_정상() {
        List<Crew> crews = CrewConvertor.getCrews(Course.FRONTEND);
        Pairs pairs = new Pairs(crews);
        for (Pair pair : pairs.getPairs()) {
            System.out.println(pair);
        }
        //테스트 어떻게하지..?
        // -> 여기서 오버라이딩 해서 shuffle하지 않고 값을 임의로 넣어줌. 해당 pair들이 있는가를 테스트.
    }

    //pairs와 pairs를 비교해서 중복이 있나 검사.
    @Test
    void 페어쌍_중복_검사_정상() {
        Pairs pairs = new Pairs(CrewConvertor.getCrews(Course.FRONTEND));
        Pairs pairs2 = new Pairs(CrewConvertor.getCrews(Course.FRONTEND));
        Assertions.assertThat(pairs.duplicatePair(pairs2)).isEqualTo(true);
    }
}