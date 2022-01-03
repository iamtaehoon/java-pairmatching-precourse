package pairmatching.code;

import java.util.Arrays;

public enum MainCode {
    MATCHING("1"), INQUERY("2"), RESET("3"), QUIT("Q");

    private String code;

    MainCode(String code) {
        this.code = code;
    }

    public static MainCode find(String inputCode) {
        return Arrays.stream(MainCode.values())
            .filter(code -> inputCode.equals(code.getCode()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당되는 코드는 존재하지 않습니다."));
    }

    private String getCode() {
        return code;
    }

}
