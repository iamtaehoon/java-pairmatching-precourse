package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드",
        "./src/main/resources/backend-crew.md"),
    FRONTEND("프론트엔드",
        "./src/main/resources/frontend-crew.md");

    private String name;
    private String path;

    Course(String name, String path) {
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }
}
