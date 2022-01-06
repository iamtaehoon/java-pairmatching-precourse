package pairmatching.service;

import static pairmatching.Constant.*;
import static pairmatching.ErrorMessage.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Crew;
import pairmatching.domain.ProgramInfo;
import pairmatching.repository.PairMatchingRepository;
import pairmatching.util.CrewConvertor;

public class PairMatchingService {
    private final PairMatchingRepository pairMatchingRepository = new PairMatchingRepository();
    private int alreadyMatchCnt = 0;

    public void makePairThisProgramInfo(ProgramInfo programInfo) {
        List<String> shuffledCrewNames = shuffleCrewsUsingProgramInfo(programInfo);
        LinkedHashMap<String, String> pairs = makePairs(shuffledCrewNames);
        remakePairIfPairIsDuplicate(programInfo, pairs);
        savePairInProgramInfo(programInfo, pairs);
    }

    private void savePairInProgramInfo(ProgramInfo programInfo, LinkedHashMap<String, String> pairs) {
        alreadyMatchCnt = 0;
        programInfo.savePairs(pairs);
        pairMatchingRepository.save(programInfo);
    }

    private List<String> shuffleCrewsUsingProgramInfo(ProgramInfo programInfo) {
        List<Crew> crews = CrewConvertor.chooseCrews(programInfo);
        List<String> crewNames = crews.stream().map(Crew::getName).collect(Collectors.toList());
        return Randoms.shuffle(crewNames);
    }

    private void remakePairIfPairIsDuplicate(ProgramInfo programInfo,
        LinkedHashMap<String, String> pairs) {
        boolean alreadyMatch = pairMatchingRepository.validateCrewsAlreadyMatch(programInfo, pairs);
        if (alreadyMatch) {
            alreadyMatchCnt += 1;
            makePairThisProgramInfo(programInfo);
        }
        if (alreadyMatchCnt >= ATTEMP_CNT) {
            alreadyMatchCnt = 0;
            throw new IllegalArgumentException(CANT_MATCHING_ERROR);
        }
    }

    private LinkedHashMap<String, String> makePairs(List<String> shuffledCrewNames) {
        LinkedHashMap<String, String> pairs = new LinkedHashMap<>();
        for (int i = 0; i < shuffledCrewNames.size() - 2; i++) {
            if (isEven(i)) {
                pairs.put(shuffledCrewNames.get(i), shuffledCrewNames.get(i + 1));
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

    private LinkedHashMap<String, String> makePairInEvenLastCase(List<String> shuffledCrewNames,
        LinkedHashMap<String, String> pairs) {
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 2),
            shuffledCrewNames.get(shuffledCrewNames.size() - 1));
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 1),
            shuffledCrewNames.get(shuffledCrewNames.size() - 2));
        return pairs;
    }

    private LinkedHashMap<String, String> makePairInOddLastCase(List<String> shuffledCrewNames,
        LinkedHashMap<String, String> pairs) {
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 2),
            shuffledCrewNames.get(shuffledCrewNames.size() - 1));
        pairs.put(shuffledCrewNames.get(shuffledCrewNames.size() - 1),
            shuffledCrewNames.get(shuffledCrewNames.size() - 3));
        return pairs;
    }

    public boolean alreadyHavePair(ProgramInfo programInfo) {
        return pairMatchingRepository.alreadyHavePair(programInfo);
    }

    public void remakePairThisProgramInfo(ProgramInfo programInfo) {
        pairMatchingRepository.delete(programInfo);
        makePairThisProgramInfo(programInfo);
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
