package pairmatching.domain;

import static pairmatching.Constant.*;

import java.util.List;
import java.util.Objects;

public class ProgramInfo {
    private Course course;
    private Level level;
    private Mission mission;
    private List<String> crewNames = null;

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

    public boolean isBackend() {
        return course == Course.BACKEND;
    }

    public boolean isFrontend() {
        return course == Course.FRONTEND;
    }

    public void savePair(List<String> crewNames) {
        this.crewNames = crewNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProgramInfo that = (ProgramInfo)o;
        return course == that.course && level == that.level && mission == that.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }

    public List<String> getCrewNames() {
        return crewNames;
    }
}
