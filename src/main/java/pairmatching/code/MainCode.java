package pairmatching.code;

import static pairmatching.ErrorMessage.*;

import java.util.Arrays;

public enum MainCode {
    MATCHING("1"), INQUERY("2"), RESET("3"), QUIT("Q");

    private final String code;

    MainCode(String code) {
        this.code = code;
    }

    public static MainCode find(String inputCode) {
        return Arrays.stream(MainCode.values())
            .filter(code -> inputCode.equals(code.getCode()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(CODE_NOT_FOUND_ERROR));
    }

    private String getCode() {
        return code;
    }

}
