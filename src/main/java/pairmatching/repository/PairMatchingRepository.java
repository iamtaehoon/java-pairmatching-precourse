package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;

import pairmatching.domain.Crew;
import pairmatching.domain.ProgramInfo;

public class PairMatchingRepository {
    private ArrayList<ProgramInfo> pairMatchingRepository = new ArrayList<>();

    public void save(ProgramInfo programInfo) {
        pairMatchingRepository.add(programInfo);
    }

    public boolean alreadyHavePair(ProgramInfo programInfo) {
        return pairMatchingRepository.contains(programInfo);
    }

    public void delete(ProgramInfo programInfo) {
        pairMatchingRepository.remove(programInfo);
    }

    public List<Crew> findPairThisProgram(ProgramInfo programInfo) {
        int programIdx = pairMatchingRepository.indexOf(programInfo);
        return pairMatchingRepository.get(programIdx).getCrews();
    }

    public boolean checkThisProgramInfoHave(ProgramInfo programInfo) {
        return pairMatchingRepository.contains(programInfo);
    }

    public void deleteAll() {
        pairMatchingRepository.clear();
    }
}
