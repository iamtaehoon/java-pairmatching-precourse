package pairmatching.service;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Pairs;
import pairmatching.domain.ProgramInfo;
import pairmatching.repository.PairMatchingRepository;
import pairmatching.util.CrewConvertor;

public class PairMatchingService {
    private PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    public Pairs matchPairsThisProgramInfo(ProgramInfo programInfo) {
        Pairs pairs = makePairsNotDuplicate(programInfo);
        programInfo.savePairs(pairs);
        pairMatchingRepository.save(programInfo);
        return pairs;
    }

    private Pairs makePairsNotDuplicate(ProgramInfo programInfo) {
        Pairs pairs = makePairs(programInfo);
        return remakePairsIfAlreadyMatch(programInfo, pairs);
    }

    private Pairs remakePairsIfAlreadyMatch(ProgramInfo programInfo, Pairs pairs) {
        int remakePairCnt = 0;
        while (pairMatchingRepository.checkThisPairsAlreadyMatch(pairs)) {
            if (remakePairCnt >= 3) {
                throw new IllegalArgumentException("매칭에 실패했습니다.");
            }
            remakePairCnt += 1;
            pairs = makePairs(programInfo);
        }
        return pairs;
    }

    private Pairs makePairs(ProgramInfo programInfo) {
        Course course = programInfo.getCourse();
        List<Crew> shuffledCrews = Randoms.shuffle(CrewConvertor.getCrews(course));
        return new Pairs(shuffledCrews);
    }

    public boolean alreadyHaveThisProgramInfo(ProgramInfo programInfo) {
        return pairMatchingRepository.contain(programInfo);
    }

    public Pairs rematchPairsThisProgramInfo(ProgramInfo programInfo) {
        pairMatchingRepository.delete(programInfo);
        matchPairsThisProgramInfo(programInfo);
        return programInfo.getPairs();
    }

    public Pairs getPairs(ProgramInfo programInfo) {
        return pairMatchingRepository.findThisProgramInfoPairs(programInfo);
    }
}
