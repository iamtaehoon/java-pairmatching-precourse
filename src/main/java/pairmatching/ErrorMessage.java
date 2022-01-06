package pairmatching;

public abstract class ErrorMessage {
    public static String FILE_NOT_FOUND_ERROR = "해당 파일은 존재하지 않습니다.";
    public static String CODE_NOT_FOUND_ERROR = "해당되는 코드는 존재하지 않습니다.";
    public static String COURSE_NOT_FOUND_ERROR = "해당되는 코스는 존재하지 않습니다.";
    public static String LEVEL_NOT_FOUND_ERROR = "해당되는 레벨은 존재하지 않습니다.";
    public static String MISSION_NOT_FOUND_ERROR = "해당되는 미션은 존재하지 않습니다.";
    public static String MISSION_NOT_FOUND_THIS_LEVEL_ERROR = "해당되는 미션은 이 단계에 존재하지 않습니다.";
    public static String CANT_MATCHING_ERROR = "매칭을 하지 못했습니다.";
    public static String DUPLICATE_CREW_NAME_ERROR = "파일에 중복된 이름의 크루가 존재합니다.";
    public static String CREW_CNT_LACK_ERROR = "해당 파일에 존재하는 크루들이 2명보다 적습니다.";
    public static String INVALID_INPUT_FORM_ERROR = "입력값의 양식이 잘못되었습니다.";
    public static String HAVE_NOT_MATCHING_ERROR = "매칭 이력이 없습니다.";
}
