package pairmatching.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.ProgramInfo;

public class PairMatchingRepository {
    private final ArrayList<ProgramInfo> pairMatchingRepository = new ArrayList<>();

    public void save(ProgramInfo programInfo) {
        pairMatchingRepository.add(programInfo);
    }

    public boolean alreadyHavePair(ProgramInfo programInfo) {
        return pairMatchingRepository.contains(programInfo);
    }

    public void delete(ProgramInfo programInfo) {
        pairMatchingRepository.remove(programInfo);
    }

    public LinkedHashMap<String, String> findPairThisProgram(ProgramInfo programInfo) {
        int programIdx = pairMatchingRepository.indexOf(programInfo);
        return pairMatchingRepository.get(programIdx).getPairs();
    }

    public boolean checkThisProgramInfoHave(ProgramInfo programInfo) {
        return pairMatchingRepository.contains(programInfo);
    }

    public void deleteAll() {
        pairMatchingRepository.clear();
    }

    public boolean validateCrewsAlreadyMatch(ProgramInfo targetProgramInfo,
        LinkedHashMap<String, String> targetPairs) {
        List<ProgramInfo> programInfosSameCourseAndLevel = pairMatchingRepository.stream()
            .filter(programInfo -> programInfo.isSameCourseAndLevel(targetProgramInfo)).collect(Collectors.toList());
        return programInfosSameCourseAndLevel.stream()
            .anyMatch(programInfo -> programInfo.alreadyMatch(targetPairs));
    }
}
