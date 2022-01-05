package pairmatching.view;

import java.util.List;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void showResult(List<String> thisProgramsPair) {
        for (int i = 0; i < thisProgramsPair.size() / 2 -1; i++) {
            System.out.println(thisProgramsPair.get(i*2) + " : " + thisProgramsPair.get(i*2 + 1));
        }
        if (thisProgramsPair.size() % 2 == 0) {
            System.out.println(thisProgramsPair.get(thisProgramsPair.size() - 2) + " : " + thisProgramsPair.get(
                thisProgramsPair.size() - 1));
        }
        if (thisProgramsPair.size() % 2 != 0) {
            System.out.println(thisProgramsPair.get(thisProgramsPair.size() - 3) + " : " + thisProgramsPair.get(
                thisProgramsPair.size() - 2) + " : " + thisProgramsPair.get(thisProgramsPair.size() - 1));
        }

    }
}
