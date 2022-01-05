package pairmatching.view;

import java.util.List;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void showResult(List<String> thisProgramsPair) {
        for (String pair : thisProgramsPair) {
            System.out.println("pair = " + pair);
        }
    }
}
