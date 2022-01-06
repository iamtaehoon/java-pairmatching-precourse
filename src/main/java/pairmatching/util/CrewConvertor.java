package pairmatching.util;

import static pairmatching.Constant.*;
import static pairmatching.ErrorMessage.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.ProgramInfo;

public class CrewConvertor {
    private static final List<Crew> backendCrews;
    private static final List<Crew> frontendCrews;

    static {
        backendCrews = CrewConvertor.makeCrewsUsingMdFile(Course.BACKEND, Course.BACKEND.getPath());
        frontendCrews = CrewConvertor.makeCrewsUsingMdFile(Course.FRONTEND, Course.FRONTEND.getPath());;
    }

    public static List<Crew> makeCrewsUsingMdFile(Course course, String filePath) {
        List<String> crewNames;
        try {
            crewNames = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new IllegalStateException(FILE_NOT_FOUND_ERROR);
        }
        crewNames = validate(crewNames);
        return makeCrewsUsingName(course, crewNames);
    }

    private static List<String> validate(List<String> crewNames) {
        validateCrewsCnt(crewNames);
        validateCrewsNameDuplicate(crewNames);
        return crewNames;
    }

    private static void validateCrewsNameDuplicate(List<String> crewNames) {
        HashSet<String> crewNamesSet = new HashSet<>(crewNames);
        if (crewNamesSet.size() != crewNames.size()) {
            throw new IllegalStateException(DUPLICATE_CREW_NAME_ERROR);
        }
    }

    private static void validateCrewsCnt(List<String> crewNames) {
        if (crewNames.size() < CREW_MIN_CNT) {
            throw new IllegalStateException(CREW_CNT_LACK_ERROR);
        }
    }

    public static List<Crew> makeCrewsUsingName(Course course, List<String> backendCrewNames) {
        return backendCrewNames.stream()
            .map(backendCrewName -> new Crew(course, backendCrewName))
            .collect(Collectors.toList());
    }


    public static List<Crew> chooseCrews(ProgramInfo programInfo) {
        if (programInfo.isBackend()) {
            return backendCrews;
        }
        if (programInfo.isFrontend()) {
            return frontendCrews;
        }
        throw new IllegalStateException(FILE_NOT_FOUND_ERROR);
    }
}
