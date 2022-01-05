package pairmatching.code;

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
            .orElseThrow(() -> new IllegalArgumentException("해당되는 코드는 존재하지 않습니다."));
    }

    private String getCode() {
        return code;
    }

}
