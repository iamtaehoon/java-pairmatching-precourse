package pairmatching.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewConvertor {
    private static List<Crew> backendCrews;
    private static List<Crew> frontendCrews;

    static {
        backendCrews = makeCrews(Course.BACKEND, Course.BACKEND.getPath());
        frontendCrews = makeCrews(Course.FRONTEND, Course.FRONTEND.getPath());
    }

    public static List<Crew> makeCrews(Course course, String pathPreProcessing) {
        Path path = Paths.get(pathPreProcessing);
        Charset cs = StandardCharsets.UTF_8;
        List<String> crewNames = getCrewNames(path, cs);
        return makeCrewsUsingNames(course, crewNames);
    }

    private static List<String> getCrewNames(Path path, Charset cs) {
        List<String> crewNames = new ArrayList<>();
        try{
            crewNames = Files.readAllLines(path, cs);
        }catch(IOException e){
            e.printStackTrace();
        }
        return crewNames;
    }

    private static List<Crew> makeCrewsUsingNames(Course course, List<String> crewNames) {
        List<Crew> temp = new ArrayList<>();
        for(String crewName : crewNames){
            temp.add(new Crew(course, crewName));
        }
        return temp;
    }

    public static List<Crew> getCrews(Course course) {
        if (course == Course.BACKEND) {
            return backendCrews;
        }
        if (course == Course.FRONTEND) {
            return frontendCrews;
        }
        throw new IllegalStateException("입력한 코스의 파일을 찾을 수 없습니다.");
    }
}
