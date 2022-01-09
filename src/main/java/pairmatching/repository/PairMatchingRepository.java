package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.code.RematchCode;
import pairmatching.domain.Pairs;
import pairmatching.domain.ProgramInfo;

public class PairMatchingRepository {
    private List<ProgramInfo> pairMatchingRepository = new ArrayList<>();

    public boolean checkThisPairsAlreadyMatch(Pairs targetPairs) {
        List<Pairs> allPairs = pairMatchingRepository.stream()
            .map(programInfo -> programInfo.getPairs())
            .collect(Collectors.toList());
        return allPairs.stream().anyMatch(pairs -> pairs.duplicatePair(targetPairs));
    }

    public void save(ProgramInfo programInfo) {
        pairMatchingRepository.add(programInfo);
    }

    public boolean contain(ProgramInfo programInfo) {
        return pairMatchingRepository.contains(programInfo);
    }

    public void delete(ProgramInfo programInfo) {
        pairMatchingRepository.remove(programInfo);
    }
}
