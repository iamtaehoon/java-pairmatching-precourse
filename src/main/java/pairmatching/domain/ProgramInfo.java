package pairmatching.domain;

import static pairmatching.Constant.*;

public class ProgramInfo {
    private Course course;
    private Level level;
    private Mission mission;

    public ProgramInfo(String[] programDetailPreProcessing) {
        this.course = Course.find(programDetailPreProcessing[COURSE_IDX]);
        this.level = Level.find(programDetailPreProcessing[LEVEL_IDX]);
        this.mission = level.findMission(programDetailPreProcessing[MISSION_IDX]);
    }

    @Override
    public String toString() {
        return "ProgramInfo{" +
            "course=" + course +
            ", level=" + level +
            ", mission=" + mission +
            '}';
    }
}
