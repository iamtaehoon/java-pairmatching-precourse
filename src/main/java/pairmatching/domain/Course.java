package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"), FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course find(String name) {
        return Arrays.stream(Course.values())
            .filter(course -> course.getName().equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당되는 코스는 존재하지 않습니다."));
    }
}
