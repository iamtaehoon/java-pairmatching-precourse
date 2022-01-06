package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    CAR_RACING("자동차경주"), LOTTO("로또"), NUMBER_BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"), PAYMENT("결제"), SUBWAY_LINE("지하철노선도"),
    PERFOMANCE_IMPROVEMENT("성능개선"), DISTRIBUTE("배포");

    private String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission find(String missionName) {
        return Arrays.stream(Mission.values())
            .filter(mission -> missionName.equals(mission.getName()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당되는 미션은 존재하지 않습니다."));

    }

    public String getName() {
        return name;
    }
}
