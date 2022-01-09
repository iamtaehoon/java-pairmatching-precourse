package pairmatching.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Arrays.asList(Mission.CAR_RACING, Mission.LOTTO, Mission.NUMBER_BASEBALL)),
    LEVEL2("레벨2", Arrays.asList(Mission.SHOPPING_CART, Mission.PAYMENT, Mission.SUBWAY_LINE)),
    LEVEL3("레벨3", Collections.EMPTY_LIST),
    LEVEL4("레벨4", Arrays.asList(Mission.PERFOMANCE_IMPROVEMENT, Mission.DISTRIBUTE)),
    LEVEL5("레벨5", Collections.EMPTY_LIST);

    private String name;
    private List<Mission> missions;

    Level(String name, List<Mission> missions) {
        this.name = name;
        this.missions = missions;
    }

    public static Level find(String name) {
        return Arrays.stream(Level.values())
            .filter(level -> name.equals(level.getName()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 레벨은 존재하지 않습니다."));
    }

    private String getName() {
        return name;
    }

    public Mission hasMission(String inputMissionName) {
        Mission inputMission = Mission.find(inputMissionName);
        return this.missions.stream()
            .filter(mission -> mission == inputMission)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("이 레벨에는 해당 미션이 존재하지 않습니다."));
    }
}
