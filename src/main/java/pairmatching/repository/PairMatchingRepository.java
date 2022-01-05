package pairmatching.repository;

import java.util.ArrayList;

import pairmatching.domain.ProgramInfo;

public class PairMatchingRepository {
    private ArrayList<ProgramInfo> pairMatchingRepository = new ArrayList<>();

    public void save(ProgramInfo programInfo) {
        pairMatchingRepository.add(programInfo);
    }
}
