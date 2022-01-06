package pairmatching.service;

import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.ProgramInfo;
import pairmatching.repository.PairMatchingRepository;

public class PairMatchingService {
    private PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    public void makePairThisProgramInfo(List<Crew> crews, ProgramInfo programInfo) {
        // TODO repository에서 검증하는거 찾긴 해야함. 근데 이건 다음에~
        // TODO 의문: Map으로 만들어야 두개가 짝이 되었는가를 찾아줄텐데, 그렇게 하면 내 원래 방식대로 돌아가버릴텐데?
        List<Crew> shuffledCrews = shuffleCrews(crews);
        programInfo.savePair(shuffledCrews);
        pairMatchingRepository.save(programInfo);
    }

    private List<Crew> shuffleCrews(List<Crew> crews) {
        List<String> crewNames = crews.stream().map(crew -> crew.getName()).collect(Collectors.toList());
        Course course = crews.get(0).getCourse();
        List<String> shuffledCrewNames = Randoms.shuffle(crewNames);
        List<Crew> shuffledCrews = shuffledCrewNames.stream()
            .map(shuffledCrewName -> new Crew(course, shuffledCrewName))
            .collect(Collectors.toList());
        return shuffledCrews;
    }

    public boolean alreadyHavePair(ProgramInfo programInfo) {
        return pairMatchingRepository.alreadyHavePair(programInfo);
    }

    public void remakePairThisProgramInfo(List<Crew> crews, ProgramInfo programInfo) {
        pairMatchingRepository.delete(programInfo);
        makePairThisProgramInfo(crews, programInfo);
    }

    public List<Crew> getThisProgramsPair(ProgramInfo programInfo) {
        return pairMatchingRepository.findPairThisProgram(programInfo);
    }

    public boolean havePairThisProgramInfo(ProgramInfo programInfo) {
        return pairMatchingRepository.checkThisProgramInfoHave(programInfo);
    }

    public void resetAllPair() {
        pairMatchingRepository.deleteAll();
    }
}
