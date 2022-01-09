package pairmatching.code;

import java.util.Arrays;

public enum MainCode {
    MATCHING("1"), INQUERY("2"), CLEAR("3"), QUIT("Q");

    private String code;

    MainCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MainCode find(String inputCode) {
        return Arrays.stream(MainCode.values())
            .filter(mainCode -> inputCode.equals(mainCode.getCode()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당되는 기능은 존재하지 않습니다."));
    }
}
