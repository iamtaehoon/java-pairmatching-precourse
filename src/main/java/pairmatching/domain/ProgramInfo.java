package pairmatching.domain;

import static pairmatching.Constant.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProgramInfo {
    private Course course;
    private Level level;
    private Mission mission;
    private LinkedHashMap<String, String> pairs = new LinkedHashMap<>();

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

    public void savePair(LinkedHashMap<String, String> pairs) {
        this.pairs = pairs;
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

    public LinkedHashMap<String, String> getPairs() {
        return pairs;
    }

    public boolean isSameCourseAndLevel(ProgramInfo nowProgramInfo) {
        return (this.course == nowProgramInfo.getCourse()) && (this.level == nowProgramInfo.getLevel());
    }

    private Course getCourse() {
        return course;
    }

    private Level getLevel() {
        return level;
    }

    public boolean alreadyMatch(LinkedHashMap<String, String> nowProgramInfoPairs) {
        for (String firstCrewName : nowProgramInfoPairs.keySet()) {
            String secondCrewName = nowProgramInfoPairs.get(firstCrewName);
            if (pairs.get(firstCrewName).equals(secondCrewName)) {
                return true;
            }
        }
        return false;
    }
}
