package pairmatching.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import pairmatching.util.CrewConvertor;

class PairsTest {

    @Test
    void 페어쌍_생성_정상() {
        List<Crew> crews = CrewConvertor.getCrews(Course.FRONTEND);
        Pairs pairs = new Pairs(crews);
        for (Pair pair : pairs.getPairs()) {
            System.out.println(pair);
        }
        //테스트 어떻게하지..?
    }

}