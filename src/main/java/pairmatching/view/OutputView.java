package pairmatching.view;

import java.util.LinkedHashMap;
import java.util.Map;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void showResult(LinkedHashMap<String, String> thisProgramsPair) {
        if (thisProgramsPair.size() % 2 == 0) {
            showResultInEven(thisProgramsPair);
            System.out.println();
            return;
        }
        showResultInOdd(thisProgramsPair);
        System.out.println();
    }

    private static void showResultInOdd(LinkedHashMap<String, String> thisProgramsPair) {
        int i = -1;
        for (String crewName : thisProgramsPair.keySet()) {
            i += 1;
            if (thisProgramsPair.size() - 3 == i) {
                String secondCrewName = thisProgramsPair.get(crewName);
                System.out.println(crewName + " : " + secondCrewName + " : " + thisProgramsPair.get(secondCrewName));
                break;
            }
            if (i % 2 == 1) {
                continue;
            }
            if (i % 2 == 0) {
                System.out.println(crewName + " : " + thisProgramsPair.get(crewName));
            }
        }

    }

    private static void showResultInEven(Map<String, String> thisProgramsPair) {
        int i = 0;
        for (String crewName : thisProgramsPair.keySet()) {
            if (i % 2 == 0) {
                System.out.println(crewName + " : " + thisProgramsPair.get(crewName));
            }
            if (i % 2 == 1) {
                i += 1;
                continue;
            }
            i += 1;
        }
    }

    public static void showResetMessage() {
        System.out.println("초기화 되었습니다.");
    }
}
