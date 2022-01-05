package pairmatching.service;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.ProgramInfo;
import pairmatching.repository.PairMatchingRepository;

public class PairMatchingService {
    private PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    public void makePairThisProgramInfo(List<String> crewNames, ProgramInfo programInfo) {
        //pair짝을 만든다.
        // TODO repository에서 검증하는거 찾긴 해야함. 근데 이건 다음에~
        List<String> shuffledCrew = Randoms.shuffle(crewNames);
        // pair를 programInfo에 넣어준다.
        programInfo.savePair(shuffledCrew);
        //repository에 programInfo를 넣는다.
        pairMatchingRepository.save(programInfo);
    }
}
