package pairmatching.view;

import java.util.LinkedHashMap;

import pairmatching.domain.Crew;
import pairmatching.domain.ProgramInfo;

public class OutputView {
    public static void showThisMatchingResult(ProgramInfo programInfo) { // TODO 홀수 짝수 나눠서 해주기.
        System.out.println("\n페어 매칭 결과입니다.");
        LinkedHashMap<Crew, Crew> pairs = programInfo.getPairs();

        if (pairs.size() % 2 == 0) {
            showPairsIfItIsEven(pairs);
            return;
        }
        showPairsIfItIsOdd(pairs);
    }

    private static void showPairsIfItIsOdd(LinkedHashMap<Crew, Crew> pairs) {
        int i = 0;
        for (Crew crew : pairs.keySet()) {
            if (i == pairs.size() - 3) {
                System.out.println(crew + " : " + pairs.get(crew) + " : " + pairs.get(pairs.get(crew)));
                break;
            }
            System.out.println(crew + " : " + pairs.get(crew));
            i += 1;
        }
        System.out.println("\n");
    }

    private static void showPairsIfItIsEven(LinkedHashMap<Crew, Crew> pairs) {
        for (Crew crew : pairs.keySet()) {
            System.out.println(crew + " : " + pairs.get(crew));
        }
        System.out.println("\n");
    }

    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

}
