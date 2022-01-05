package pairmatching.service;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.ProgramInfo;
import pairmatching.repository.PairMatchingRepository;

public class PairMatchingService {
    private PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    public void makePairThisProgramInfo(List<String> crewNames, ProgramInfo programInfo) {
        // TODO repository에서 검증하는거 찾긴 해야함. 근데 이건 다음에~
        List<String> shuffledCrew = Randoms.shuffle(crewNames);
        programInfo.savePair(shuffledCrew);
        pairMatchingRepository.save(programInfo);
    }

    public boolean alreadyHavePair(ProgramInfo programInfo) {
        return pairMatchingRepository.alreadyHavePair(programInfo);
    }

    public void remakePairThisProgramInfo(List<String> crewNames, ProgramInfo programInfo) {
        pairMatchingRepository.delete(programInfo);
        makePairThisProgramInfo(crewNames, programInfo);
    }

    public List<String> getThisProgramsPair(ProgramInfo programInfo) {
        return pairMatchingRepository.findPairThisProgram(programInfo);
    }

    public boolean havePairThisProgramInfo(ProgramInfo programInfo) {
        return pairMatchingRepository.checkThisProgramInfoHave(programInfo);
    }
}
