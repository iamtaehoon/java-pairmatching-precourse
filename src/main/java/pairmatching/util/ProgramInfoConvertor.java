package pairmatching.util;

import static pairmatching.Constant.*;

import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.ProgramInfo;

public class ProgramInfoConvertor {

    public static ProgramInfo makeProgramInfo(String programInfoPreProcessing) {
        String[] programInfoDetailsPreProcessing = programInfoPreProcessing.split(", ");
        if (programInfoDetailsPreProcessing.length != DETAIL_CNT) {
            throw new IllegalArgumentException("입력값의 양식이 잘못되었습니다.");
        }
        Course course = Course.find(programInfoDetailsPreProcessing[COURSE_IDX]);
        Level level = Level.find(programInfoDetailsPreProcessing[LEVEL_IDX]);
        Mission mission = level.hasMission(programInfoDetailsPreProcessing[MISSION_IDX]);
        return new ProgramInfo(course, level, mission);

    }
}
