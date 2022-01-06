package pairmatching.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Crew;
import pairmatching.domain.ProgramInfo;
import pairmatching.repository.PairMatchingRepository;

public class PairMatchingService {
    private PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();

    public void makePairThisProgramInfo(List<Crew> crews, ProgramInfo programInfo) {

        List<String> crewNames = crews.stream().map(crew -> crew.getName()).collect(Collectors.toList());
        List<String> shuffledCrewNames = Randoms.shuffle(crewNames);
        LinkedHashMap<String, String> pairs = makePair(shuffledCrewNames);
        // pairMatchingRepository.validateCrewsAlreadyMatch();
        // TODO repository에서 검증하는거 찾긴 해야함. 근데 이건 다음에~
        // TODO 의문: Map으로 만들어야 두개가 짝이 되었는가를 찾아줄텐데, 그렇게 하면 내 원래 방식대로 돌아가버릴텐데?
        programInfo.savePair(pairs);
        pairMatchingRepository.save(programInfo);
    }

    private LinkedHashMap<String, String> makePair(List<String> shuffledCrewNames) {
        LinkedHashMap<String, String> pairs = new LinkedHashMap<>();
        for (int i = 0; i < shuffledCrewNames.size()-2; i++) {
            if (isEven(i)) {
                pairs.put(shuffledCrewNames.get(i),shuffledCrewNames.get(i+1));
            }
            if (!isEven(i)) {
                pairs.put(shuffledCrewNames.get(i), shuffledCrewNames.get(i - 1));
            }
        }
        if (isEven(shuffledCrewNames.size())) {
            return makePairInEvenLastCase(shuffledCrewNames, pairs);
        }
        return makePairInOddLastCase(shuffledCrewNames, pairs);
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
    }

    private LinkedHashMap<String, String> makePairInEvenLastCase(List<String> shuffledCrewNames, LinkedHashMap<String, String> pairs) {
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 2),
            shuffledCrewNames.get(shuffledCrewNames.size() - 1));
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 1),
            shuffledCrewNames.get(shuffledCrewNames.size() - 2));
        return pairs;
    }

    private LinkedHashMap<String, String> makePairInOddLastCase(List<String> shuffledCrewNames, LinkedHashMap<String, String> pairs) {
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 2),
            shuffledCrewNames.get(shuffledCrewNames.size() - 1));
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 1),
            shuffledCrewNames.get(shuffledCrewNames.size() - 3));
        return pairs;
    }

    public boolean alreadyHavePair(ProgramInfo programInfo) {
        return pairMatchingRepository.alreadyHavePair(programInfo);
    }

    public void remakePairThisProgramInfo(List<Crew> crews, ProgramInfo programInfo) {
        pairMatchingRepository.delete(programInfo);
        makePairThisProgramInfo(crews, programInfo);
    }

    public LinkedHashMap<String, String> getThisProgramsPair(ProgramInfo programInfo) {
        return pairMatchingRepository.findPairThisProgram(programInfo);
    }

    public boolean havePairThisProgramInfo(ProgramInfo programInfo) {
        return pairMatchingRepository.checkThisProgramInfoHave(programInfo);
    }

    public void resetAllPair() {
        pairMatchingRepository.deleteAll();
    }
}
