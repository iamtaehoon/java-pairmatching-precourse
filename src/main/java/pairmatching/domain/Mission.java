package pairmatching.domain;

import static pairmatching.ErrorMessage.*;

import java.util.Arrays;

public enum Mission {
    CAR_RACING("자동차경주"), LOTTO("로또"), NUMBER_BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"), PAYMENT("결제"), SUBWAY_LINE("지하철노선도"),
    PERFOMANCE_IMPROVEMENT("성능개선"), DISTRIBUTE("배포");

    private final String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission find(String missionName) {
        return Arrays.stream(Mission.values())
            .filter(mission -> missionName.equals(mission.getName()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(MISSION_NOT_FOUND_ERROR));

    }

    public String getName() {
        return name;
    }
}
