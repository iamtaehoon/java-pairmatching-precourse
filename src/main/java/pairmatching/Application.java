package pairmatching;

import pairmatching.util.CrewConvertor;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        CrewConvertor.makeCrewsUsingMdFile("./src/main/resources/backend-crew.md");
        CrewConvertor.makeCrewsUsingMdFile("./src/main/resources/frontend-crew.md");

    }
}
