package pairmatching.domain;

import java.util.Objects;

public class ProgramInfo {
    private Course course;
    private Level level;
    private Mission mission;
    private Pairs pairs = null;

    public ProgramInfo(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
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

    @Override
    public String toString() {
        return "ProgramInfo{" +
            "course=" + course +
            ", level=" + level +
            ", mission=" + mission +
            ", pairs=" + pairs +
            '}';
    }
}
