package pairmatching.view;

import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR]" + e.getMessage());
    }

    public static void showPairs(Pairs pairs) {
        for (Pair pair : pairs.getPairs()) {
            System.out.println(pair);
        }
    }
}
