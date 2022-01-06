package pairmatching.code;

import static pairmatching.ErrorMessage.*;

import java.util.Arrays;

public enum RematchCode {
    YES("네"), NO("아니오");

    private String code;

    RematchCode(String code) {
        this.code = code;
    }

    public static RematchCode find(String inputCode) {
        return Arrays.stream(RematchCode.values())
            .filter(code -> inputCode.equals(code.getCode()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(CODE_NOT_FOUND_ERROR));
    }

    private String getCode() {
        return code;
    }

}
