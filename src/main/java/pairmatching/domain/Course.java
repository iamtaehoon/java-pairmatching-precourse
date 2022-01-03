package pairmatching.domain;

import java.util.Arrays;

import pairmatching.code.MainCode;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course find(String inputCourse) {
        return Arrays.stream(Course.values())
            .filter(course -> inputCourse.equals(course.getName()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당되는 코스는 존재하지 않습니다."));
    }

    private String getName() {
        return name;
    }
}

