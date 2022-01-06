package pairmatching.util;

import static pairmatching.Constant.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewConvertor {
    public static List<Crew> makeCrewsUsingMdFile(Course course, String filePath) {
        List<String> crewNames = null;
        try {
            crewNames = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new IllegalStateException("해당 파일은 존재하지 않습니다.");
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
        HashSet<String> crewNamesSet = new HashSet<>();
        crewNamesSet.addAll(crewNames);
        if (crewNamesSet.size() != crewNames.size()) {
            throw new IllegalStateException("파일에 중복된 이름의 크루가 존재합니다.");
        }
    }

    private static void validateCrewsCnt(List<String> crewNames) {
        if (crewNames.size() < CREW_MIN_CNT) {
            throw new IllegalStateException("해당 파일에 존재하는 크루들이 2명보다 적습니다.");
        }
    }

    public static List<Crew> makeCrewsUsingName(Course course, List<String> backendCrewNames) {
        return backendCrewNames.stream()
            .map(backendCrewName -> new Crew(course, backendCrewName))
            .collect(Collectors.toList());
    }
}
