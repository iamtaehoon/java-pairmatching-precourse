package pairmatching.domain;

import static pairmatching.ErrorMessage.*;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드", "./src/main/resources/backend-crew.md"),
    FRONTEND("프론트엔드", "./src/main/resources/frontend-crew.md");

    private String name;
    private String path;

    Course(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static Course find(String inputCourse) {
        return Arrays.stream(Course.values())
            .filter(course -> inputCourse.equals(course.getName()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(COURSE_NOT_FOUND_ERROR));
    }

    private String getName() {
        return name;
    }
}

