package pairmatching.controller;

import pairmatching.code.MainCode;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    // private PairMatchingService = new PairMatchingService();
    private MainCode mainCode = null;

    public void run() {
        while (mainCode != MainCode.QUIT) {
            mainCode = chooseMainFunction();
            executeFunction(mainCode);
        }
    }

    private void executeFunction(MainCode mainCode) {
    }

    private MainCode chooseMainFunction() {
        try {
            return MainCode.find(InputView.enterMainFunction());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            return chooseMainFunction();
        }
    }
}
