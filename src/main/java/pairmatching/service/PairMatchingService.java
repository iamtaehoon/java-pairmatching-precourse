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
    private int alreadyMatchCnt = 0;

    public void makePairThisProgramInfo(List<Crew> crews, ProgramInfo programInfo) {
        List<String> crewNames = crews.stream().map(crew -> crew.getName()).collect(Collectors.toList());
        List<String> shuffledCrewNames = Randoms.shuffle(crewNames);
        LinkedHashMap<String, String> pairs = makePair(shuffledCrewNames);
        programInfo.savePair(pairs);
        validateCrewsAlreadyMatch(crews, programInfo);
        alreadyMatchCnt = 0;
        pairMatchingRepository.save(programInfo);
    }

    private void validateCrewsAlreadyMatch(List<Crew> crews, ProgramInfo programInfo) {
        boolean alreadyMatch = pairMatchingRepository.validateCrewsAlreadyMatch(programInfo);
        if (alreadyMatch) {
            alreadyMatchCnt += 1;
            makePairThisProgramInfo(crews, programInfo);
        }
        if (alreadyMatchCnt >= 3) {
            alreadyMatchCnt = 0;
            throw new IllegalArgumentException("매칭을 하지 못했습니다.");
        }
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
